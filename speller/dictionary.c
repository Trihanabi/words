// Implements a dictionary's functionality
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <strings.h>
#include <ctype.h>
#include <stdlib.h>
#include "dictionary.h"

// Number of buckets in hash table
#define N 249997

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
    int num;
    int rank;
    bool in_dictionary;
}
node;

// Hash table
node *table[N];
// Count: number of words in the dictionary
int count = 0;
// Times: occurrence of words in the dictionary appear in the article  
int times = 0;
// Total_words: number of words in the dictionary and article
int total_words = 0;

// Returns true if word is in dictionary else false
bool check(const char *word)
{
    // get the head of the node of the linked list for the word based on its hash value
    char word1[LENGTH + 1];
    strcpy(word1, word);
    int length = strlen(word1);
    for (int i=0; i<length; i++)
    {
        word1[i] = tolower(word1[i]);
    }
    unsigned int address = hash(word1);
    node *p = table[address];
    // while p is not get the end of linked list which has null value
    while (p != NULL)
    {
        // check if the word matches any of the word in the dictionary
        if(strcasecmp(word1, p->word) == 0 && p->in_dictionary == true)
        {
            times++;  
            p->num++;
            return true;
        }
        else if(strcasecmp(word1, p->word) == 0 && p->in_dictionary == false)
        {
            p->num++;
            return true;
        }
        // progress to next node
        p = p -> next;
    }
    // free(p);
    insert(word1);
    return false;
}

// Hashes word to a number
unsigned int hash(const char *word)                // N is small?Hash function can be quicker.
{
    unsigned int seed=131 ;// 31 131 1313 13131 131313 etc..
    unsigned int hash=0 ;
    while(*word)
    {
        hash=hash*seed+(*word++);
    }
    return(hash % N);
}

// Loads dictionary into memory, returning true if successful else false
bool load(const char *dictionary)
{
    // open the dictionary file
    FILE *fp;
    fp = fopen(dictionary, "r");
    // if file is empty, close the file and return false
    if (fp == NULL)
    {
        puts("file open failed");
        return false;
    }
    // declare a variable represent each word of dictionary
    char input[LENGTH + 1];
    unsigned int address;
    // read file until file reach EOF
    while(fscanf(fp, "%s", input) != EOF)
    {
        // malloc a new node for each word if malloc does not return NULL.
        node *n = malloc(sizeof(node));
        // check if malloc failed
        if( n == NULL )
        {
            puts("malloc failed!");
            free(n);
            fclose(fp);
            return false;
        }
        // copy the string value of word into the node's word
        strcpy(n->word, input);
        // no problem, because each word in dictionary is lowercase
        // set the pointer of new node to the first node of returned hash value
        address = hash(input);
        n->num = 1;
        n->rank = 0;
        n->in_dictionary = true;
        n->next = table[address];
        // set the head of the table pointing to the new node
        table[address] = n;
        // Count means number of words in the dictionary
        count ++;
    }
    // total words = words are in the dictionary + words are not in the dictionary
    total_words = count;
    fclose(fp);
    return true;
}

// Returns number of words in dictionary if loaded else 0 if not yet loaded
unsigned int size(void)
{
    return count;
}

// Unloads dictionary from memory, returning true if successful else false
bool unload(void)
{
    // iterate through each of the bucket
    for(int i=0; i<N; i++)
    {
        node *cursor = table[i];
        while (cursor!= NULL)
        {
            node *tmp = cursor;
            cursor = cursor->next;
            free(tmp);
        }
        if (i == N - 1 && cursor == NULL)
        {
            return true;
        }
    }
    return false;
}

unsigned int occurrence(void)
{
    return times;
}

unsigned int num_new_words(void)
{
    return total_words-count;
}


void rank_words(void)
{
    for (int words = 0; words < total_words; words++ )
    {
        node *max = malloc(sizeof(node));
        node *p = malloc(sizeof(node));
        max->num = 0;
        for (int i = 0; i < N; i++)
        {
            if (table[i] == NULL )
                continue;
            p = table[i];
            do
            {
                if ((max->num < p->num) && (p->rank == 0))
                {
                    max = p;
                }
                p = p->next;
            } while(p != NULL);
        }
        max->rank = words+1;
    }
}

void display(void)
{
    for (int words = 0; words < total_words; words++)
    {
        bool flag = true;
        // loop to find the rank
        for (int i = 0; i < N && flag ; i++)
        {
            if (table[i] == NULL)
            {
                continue;
            }
            node *p = table[i];
            while (p != NULL)
            {
                if (p->rank == (words+1))
                {
                    printf("%-4d%-45s%-4d  %d\n",p->rank, p->word, p->num, p->in_dictionary);
                    flag = false;
                    break;
                }
                p = p->next;
            }
        }
    }
}

bool record(char *text)
{
    FILE *fp;
    // build new file name
    char* dest = strtok(text, ".");
    printf("%s\n", dest);
    dest = strstr(dest, "/");
    dest++;
    char* suffix = "_record.txt";
    printf("%s\n", dest);
    strcat(dest, suffix);
    printf("%s\n", dest);
    fp = fopen(dest, "w");

    // if file is empty, close the file and return false
    if (fp == NULL)
    {
        puts("file open failed");
        return false;
    }

    for (int words = 0; words < total_words; words++)
    {
        bool flag = true;
        for (int i = 0; i < N && flag ; i++)
        {
            if (table[i] == NULL)
                continue;
            node *p = table[i];
            while (p != NULL)
            {
                if (p->rank == (words+1))
                {
                    fprintf(fp, "%-5d%-45s%-4d  %d\n",p->rank, p->word, p->num, p->in_dictionary);
                    flag = false;
                    break;
                }
                p = p->next;
            }
        }
    }
    fclose(fp);
    return true;
}

bool insert(const char *word)
{
    unsigned int address = hash(word);
    // total words need to add words are not in the dictionary
    total_words++;
    node *n = malloc(sizeof(node));
    if( n == NULL )
    {
        puts("malloc failed!");
        free(n);
        return false;
    }
    // copy the string value of word into the node's word
    strcpy(n->word, word);
    // no problem, because each word in dictionary is lowercase
    // set the pointer of new node to the first node of returned hash value
    n->num = 1;
    n->rank = 0;
    n->in_dictionary = false;
    n->next = table[address];
    // set the head of the table pointing to the new node
    table[address] = n;
    // Count means number of words in the dictionary
    return true;
}