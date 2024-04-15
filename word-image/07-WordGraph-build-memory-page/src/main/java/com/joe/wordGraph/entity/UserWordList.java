package com.joe.wordGraph.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// to build user word list
public class UserWordList {

    private List<UserWord> words = new ArrayList<>();

    public UserWordList() {
    }

    public List<UserWord> getWords() {
        return words;
    }

    public void setWords(List<UserWord> words) {
        this.words = words;
    }

    public void addWord(UserWord theUserWord) { words.add(theUserWord); }

    @Override
    public String toString() {
        return "UserWordList{" +
                "words=" + words +
                '}';
    }

}
