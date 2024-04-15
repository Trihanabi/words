package com.joe.wordGraph.entity;

import jakarta.persistence.*;

public class UserWord {

    private String word;
    private int occurence_freqence;
    private int memory_level = 0;
    private int memory_interval = Integer.MAX_VALUE;
    private boolean is_memory = false;
    private long first_appearance = Integer.MAX_VALUE;
    private int fk_word = -1;

    public UserWord(String word, int occurence_freqence) {
        this.word = word;
        this.occurence_freqence = occurence_freqence;
    }

    public UserWord(String word, int occurence_freqence,
                    int memory_level, int memory_interval,
                    boolean is_memory, long first_appearance,
                    int fk_word) {
        this.word = word;
        this.occurence_freqence = occurence_freqence;
        this.memory_level = memory_level;
        this.memory_interval = memory_interval;
        this.is_memory = is_memory;
        this.first_appearance = first_appearance;
        this.fk_word = fk_word;
    }

    public int getFk_word() {
        return fk_word;
    }

    public void setFk_word(int fk_word) {
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

    public int getMemory_level() {
        return memory_level;
    }

    public void setMemory_level(int memory_level) {
        this.memory_level = memory_level;
    }

    public int getMemory_interval() {
        return memory_interval;
    }

    public void setMemory_interval(int memory_interval) {
        this.memory_interval = memory_interval;
    }

    public boolean isIs_memory() {
        return is_memory;
    }

    public void setIs_memory(boolean is_memory) {
        this.is_memory = is_memory;
    }

    public long getFirst_appearance() {
        return first_appearance;
    }

    public void setFirst_appearance(long first_appearance) {
        this.first_appearance = first_appearance;
    }

    @Override
    public String toString() {
        return "UserWord{" +
                ", word='" + word + '\'' +
                ", occurence_freqence=" + occurence_freqence +
                ", memory_level=" + memory_level +
                ", memory_interval=" + memory_interval +
                ", is_memory=" + is_memory +
                ", first_appearance=" + first_appearance +
                ", fk_word=" + fk_word +
                '}';
    }
}
