package com.joe.wordGraph.dao;

import com.joe.wordGraph.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByName(String name);
}
