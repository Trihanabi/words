package com.joe.wordGraph.service;

import java.util.List;

import com.joe.wordGraph.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    List<Book> findAll();

    Book findById(int theId);

    void save(Book theEmployee);

    void deleteById(int theId);

    boolean isContainName(String name);
}
