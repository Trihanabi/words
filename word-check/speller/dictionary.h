// Declares a dictionary's functionality

#ifndef DICTIONARY_H
#define DICTIONARY_H

#include <stdbool.h>

// Maximum length for a word
// (e.g., pneumonoultramicroscopicsilicovolcanoconiosis)
#define LENGTH 45

// Prototypes
bool check(const char *word);
unsigned int hash(const char *word);
bool load(const char *dictionary);
unsigned int size(void);
bool unload(void);
unsigned int occurrence(void);
void display(void);
void rank_words(void);
bool insert(const char *word);
unsigned int num_new_words(void);
bool record(char *text);


#endif // DICTIONARY_H
