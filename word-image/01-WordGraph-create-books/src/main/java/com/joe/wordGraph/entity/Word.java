package com.joe.wordGraph.entity;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.JsonAsStringJdbcType;

@Entity
@Table(name="words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "word")
    private String word;

    @Column(name = "chinese")
    private String chinese;

    @Column(name = "phonetic_symbol")
    private String phonetic_symbol;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "graph_id")
    private Graph graph;

    public Word(String word, String chinese, String phonetic_symbol, Graph graph) {
        this.word = word;
        this.chinese = chinese;
        this.phonetic_symbol = phonetic_symbol;
        this.graph = graph;
    }

    public Word(String word, String phonetic_symbol, Graph graph) {
        this.word = word;
        this.phonetic_symbol = phonetic_symbol;
        this.graph = graph;
    }

    public Word(String word, Graph graph) {
        this.word = word;
        this.graph = graph;
    }

    public Word(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getPhonetic_symbol() {
        return phonetic_symbol;
    }

    public void setPhonetic_symbol(String phonetic_symbol) {
        this.phonetic_symbol = phonetic_symbol;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", chinese='" + chinese + '\'' +
                ", phonetic_symbol='" + phonetic_symbol + '\'' +
                ", graph=" + graph +
                '}';
    }
}