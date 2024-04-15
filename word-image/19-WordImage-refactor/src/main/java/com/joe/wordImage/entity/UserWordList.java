package com.joe.wordImage.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * 用于构建单词列表
 */
public class UserWordList {

    private List<UserWord> words = new ArrayList<>();

    public UserWordList(List<UserWord> words) {
        this.words = words;
    }

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
