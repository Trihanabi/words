// Implements a dictionary's functionality

#include <stdbool.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <string.h>
#include "dictionary.h"

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
}
node;

// Number of buckets in hash table
const unsigned int N = 26;

int word_count = 0;

// Hash table
node *table[N];

// Returns true if word is in dictionary, else false
bool check(const char *word)
{
    // get the head of the node of the linked list for the word based on its hash value
    node *ptr = table[hash(word)];

    //while it hasn't reach end of the linked list which has null value
    while (ptr != NULL)
    {
        //check if the word matches any of the word in the dictionary stored in the table
        if (strcasecmp(word, ptr->word) == 0)
        {
            return true;
        }
        //progress to next node
        ptr = ptr->next;
    }
    unload();
    return false;
}

// Hashes word to a number
unsigned int hash(const char *word)
{
    // return a number specifying the bucket the word should be stored in
    unsigned long hash = 5381;
    int c;
    while ((c = tolower(*word++)))
    {
        hash = ((hash << 5) + hash) + c; /* hash * 33 + c */
    }
    return hash % N;
    return 0;
}

// Loads dictionary into memory, returning true if successful, else false
bool load(const char *dictionary)
{
    // open a dictionary file
    FILE *dict = fopen(dictionary, "r");
    if (dict == NULL)
    {
        printf("Could not open %s.\n", dictionary);
        return false;
    }

    char word[LENGTH + 1];
    // read strings from file one at a time until reach EOF
    while(fscanf(dict, "%s", word) != EOF)
    {
        //read string and store in the var word
        // malloc a new node for each word if malloc doesn't return NULL.
        node *n = malloc(sizeof(node));

        if (n == NULL)
        {
            free(n);
            fclose(dict);
            return false;
        }
        else
        {
            //get hash number of the bucket for the word from hash function
            int hash_number = hash(word);

            //copy the string value of the word into the node's word value
            strcpy(n->word, word);

            //set the pointer of the new node to the returned hash number
            n->next = table[hash_number];

            //set the head of the table pointing to the new node
            table[hash_number] = n;

            //increase number of word count
            word_count++;
        }
    }

    fclose(dict);
    return true;
}

// Returns number of words in dictionary if loaded, else 0 if not yet loaded
unsigned int size(void)
{
    // return word_count which has been incrementing in the load function
    return word_count;
}

// Unloads dictionary from memory, returning true if successful, else false
bool unload(void)
{
    //iterate through each of the bucket
    for (int i = 0; i < N; i++)
    {
        //cursor at the initial table location
        node *cursor = table[i];

        //while yet to reach end of the linked list (which has value of null)
        while (cursor != NULL)
        {
            // Create a temporary cursor to store address of the current node
            node *tmp = cursor;
            // Move the actual cursor to next node
            cursor = cursor->next;
            // Free up the node that the temporary cursor is pointing to
            free(tmp);
        }
        if (i == N - 1 && cursor == NULL)
        {
            return true;
        }
    }
    return false;
}