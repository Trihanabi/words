package com.joe.wordGraph.entity;


public class WordData {

    private UserWordList userWordList;

    public WordData() {
    }

    public WordData(UserWordList userWordList) {
        this.userWordList = userWordList;
    }

    public UserWordList getUserWordList() {
        return userWordList;
    }

    public void setUserWordList(UserWordList userWordList) {
        this.userWordList = userWordList;
    }

    @Override
    public String toString() {
        return "WordData{" +
                "userWordList=" + userWordList +
                '}';
    }
}
