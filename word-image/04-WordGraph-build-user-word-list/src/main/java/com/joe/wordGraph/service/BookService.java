package com.joe.wordGraph.service;

import java.util.List;

import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.BookWord;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    List<Book> findAll();

    Book findById(int theId);

    void save(Book theBook);

    void deleteById(int theId);

    boolean isContainName(String name);

    List<BookWord> createBookWordList(Book theBook);
}
