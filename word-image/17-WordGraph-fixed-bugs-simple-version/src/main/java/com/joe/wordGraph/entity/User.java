package com.joe.wordGraph.entity;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.JsonAsStringJdbcType;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    // Todo: does it need word list of user?

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
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

//    public List<JsonAsStringJdbcType> getUserWordList() {
//        return userWordList;
//    }
//
//    public void setUserWordList(List<JsonAsStringJdbcType> userWordList) {
//        this.userWordList = userWordList;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", userWordList=" + userWordList +
                ", books=" + books +
                '}';
    }
}
