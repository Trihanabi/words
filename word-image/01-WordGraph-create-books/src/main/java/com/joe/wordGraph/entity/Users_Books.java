package com.joe.wordGraph.entity;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.JsonAsStringJdbcType;

//@Entity
@Table(name = "users_books")
public class Users_Books {

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book course;

    @Column(name="word_list")
    private JsonAsStringJdbcType userWordList;

    public Users_Books(User user, Book course, JsonAsStringJdbcType userWordList) {
        this.user = user;
        this.course = course;
        this.userWordList = userWordList;
    }

    public Users_Books(User user, Book course) {
        this.user = user;
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getCourse() {
        return course;
    }

    public void setCourse(Book course) {
        this.course = course;
    }

    public JsonAsStringJdbcType getUserWordList() {
        return userWordList;
    }

    public void setUserWordList(JsonAsStringJdbcType userWordList) {
        this.userWordList = userWordList;
    }

    @Override
    public String toString() {
        return "Users_Books{" +
                "user=" + user +
                ", course=" + course +
                ", userWordList=" + userWordList +
                '}';
    }
}
