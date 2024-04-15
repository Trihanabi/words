package com.joe.wordGraph.entity;

public class BookWord {

    private String word;
    private int occurence_freqence;
    private long first_appearance = Integer.MAX_VALUE;
    private int fk_word = -1;

    public BookWord(String word, int occurence_freqence) {
        this.word = word;
        this.occurence_freqence = occurence_freqence;
    }

    public BookWord(String word, int occurence_freqence,
                    long first_appearance, int fk_word) {
        this.word = word;
        this.occurence_freqence = occurence_freqence;
        this.first_appearance = first_appearance;
        this.fk_word = fk_word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getOccurence_freqence() {
        return occurence_freqence;
    }

    public void setOccurence_freqence(int occurence_freqence) {
        this.occurence_freqence = occurence_freqence;
    }

    public long getFirst_appearance() {
        return first_appearance;
    }

    public void setFirst_appearance(long first_appearance) {
        this.first_appearance = first_appearance;
    }

    public int getFk_word() {
        return fk_word;
    }

    public void setFk_word(int fk_word) {
        this.fk_word = fk_word;
    }
}
