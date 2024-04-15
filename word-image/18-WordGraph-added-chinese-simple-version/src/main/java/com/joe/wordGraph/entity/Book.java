package com.joe.wordGraph.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.JsonAsStringJdbcType;
import org.json.JSONArray;

import java.util.List;
import java.util.Set;


@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="JSON", name="word_list")
    private String wordList;

    @OneToOne(fetch = FetchType.LAZY,
              cascade = {CascadeType.PERSIST, CascadeType.MERGE,
              CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "graph_id")
    private Graph graph;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    public Book() {
    }

    public Book(String name, String wordList, Graph graph) {
        this.name = name;
        this.wordList = wordList;
        this.graph = graph;
    }

    public Book(String name, Graph graph) {
        this.name = name;
        this.graph = graph;
    }

    public Book(String name, String wordList) {
        this.name = name;
        this.wordList = wordList;
    }

    public Book(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWordList() {
        return wordList;
    }

    public void setWordList(String wordList) {
        this.wordList = wordList;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wordList=" + wordList +
                ", graph=" + graph +
                ", users=" + users +
                '}';
    }
}
