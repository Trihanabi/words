package com.joe.wordImage.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * 用户和书籍的关系表，并存放用户书籍单词列表
 */
@Entity
@Table(name = "users_books")
public class Users_Books {

    @EmbeddedId
    private UserBookId id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @MapsId("bookId")
    Book book;

    // 专属于用户的书籍单词列表
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="JSON", name="word-list")
    private String userWordList;

    public Users_Books() {
    }

    public Users_Books(User user, Book book, String userWordList) {
        this.user = user;
        this.book = book;
        this.id = new UserBookId(user.getId(), book.getId());
        this.userWordList = userWordList;
    }

    public Users_Books(User user, Book book) {
        this.user = user;
        this.book = book;
        this.id = new UserBookId(user.getId(), book.getId());
    }

    public UserBookId getId() {
        return id;
    }

    public void setId(UserBookId userBookId) {
        this.id = userBookId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getUserWordList() {
        return userWordList;
    }

    public void setUserWordList(String userWordList) {
        this.userWordList = userWordList;
    }

    @Override
    public String toString() {
        return "Users_Books{" +
                "user=" + user +
                ", course=" + book +
                ", userWordList=" + userWordList +
                '}';
    }

    @Embeddable
    public static class UserBookId {
        private int userId;
        private int bookId;

        public UserBookId() {
        }

        public UserBookId(int userId, int bookId) {
            this.userId = userId;
            this.bookId = bookId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        @Override
        public String toString() {
            return "UserBookId{" +
                    "userId=" + userId +
                    ", bookId=" + bookId +
                    '}';
        }
    }
}
