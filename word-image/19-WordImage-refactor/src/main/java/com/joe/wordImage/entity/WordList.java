package com.joe.wordImage.entity;


import java.util.List;

// to bind the form data in the book_words.html
public class WordList {

    private List<String> wordsAndOcc;

    public WordList() {
    }

    public List<String> getWordsAndOcc() {
        return wordsAndOcc;
    }

    public void setWordsAndOcc(List<String> wordsAndOcc) {
        this.wordsAndOcc = wordsAndOcc;
    }

    @Override
    public String toString() {
        return "UserWordList{" +
                "words=" + wordsAndOcc +
                '}';
    }
}

