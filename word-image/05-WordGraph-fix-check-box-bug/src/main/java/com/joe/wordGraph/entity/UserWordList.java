package com.joe.wordGraph.entity;


import java.util.List;

// to build user word list
public class UserWordList {

    private List<String> words;

    public UserWordList() {
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "UserWordList{" +
                "words=" + words +
                '}';
    }
}
