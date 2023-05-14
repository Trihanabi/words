# words_check
### Background
This program based on the Harvard CS50 word problem set. The goal is to quickly count the number of words and 
distinguish words of different difficulty, to help English learners learn words in articles or books.

### User's guide:
1. dictionaries folder: dictionaries\
   large:&nbsp;&nbsp;&nbsp;&nbsp; a big dictionary include 143091 words(from CS50)\
   2000_word.txt:&nbsp;&nbsp;&nbsp;&nbsp; Top 2000 Vocabulary Words
3. texts: articles or books
4. ~/word/speller/ ./speller dictionaries/2000_word.txt texts/gatsby.txt \
   File Directory &nbsp;&nbsp;&nbsp;[Program] [Dictionary] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Book/Article]

### Measuring impact
1. It can simplify my learning english - In progress
2. Someone can use it to learn english - In plan

### Goals
1. Quickly generate a word dictionary with the number of occurrences from an English book. - Complete
2. Generate a difficult word list - In progress
3. Add python version - Not start - Not start

### Plan & Next steps
- [x] 1. It can give an dictionary, this program can analysis dictionary word percentage of word. √
- [x] 2. It can count the frequency of words. √
- [ ] 3. Add more common words list. 
&nbsp;&nbsp;&nbsp;&nbsp;- [] 3.1 2000 words list with videos or audios.
&nbsp;&nbsp;&nbsp;&nbsp;- [] 3.2 speaking and writing Words lists in Longman Dictionary.
- [ ] 4. Hard word list can build word Anki Memory Cards automatically

### Implementation details(dictionary.c file):
1.1 The first function we can build a hash table of words in dictionary firstly.\
1.2 Then use check() to check if word in dictionary and calculate percentage of word.\
2.1 The second function we need to count the number of occurrences.\
&nbsp;&nbsp;&nbsp;2.1.1 Build a hash table of this article.\
&nbsp;&nbsp;&nbsp;2.1.2 Check if this word is repeated.\
&nbsp;&nbsp;&nbsp;2.1.3 If repeated, add number of this word occurrence.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If it is not repeated, add this word to hashtable of this article.

### Open Questions
