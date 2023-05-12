# words_check
### Background
This program based on the Harvard CS50 word problem set. The goal is to quickly count the number of words and 
distinguish words of different difficulty, to help English learners learn words in articles or books.

### User's guide:
1. dictionaries folder: dictionaries
2. texts: articles or books
3. ~/word/speller/ ./speller dictionaries/2000_word.txt texts/gatsby.txt 
   File Directory    Program Dictionary                 Book/Article

### Measuring impact
1. It can simplify my learning english - In progress
2. Someone can use it to learn english - In plan

### Goals
1. Quickly generate a word dictionary with the number of occurrences from an English book. - Complete
2. Generate a difficult word list - Not start
3. Add python version - Not start - Not start

### Plan
1. It can give an dictionary, this program can analysis dictionary word percentage of word.
2. It can count the frequency of words.

### Implementation details(dictionary.c file):
1.1 The first function we can build a hash table of words in dictionary firstly.\
1.2 Then use check() to check if word in dictionary and calculate percentage of word.\
2.1 The second function we need to count the number of occurrences.\
   2.1.1 Build a hash table of this article.\
   2.1.2 Check if this word is repeated.\
   2.1.3 If repeated, add number of this word occurrence.\
         If it is not repeated, add this word to hashtable of this article.

### Open Questions
