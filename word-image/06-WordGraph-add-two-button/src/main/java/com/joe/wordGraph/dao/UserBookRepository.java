package com.joe.wordGraph.dao;

import com.joe.wordGraph.entity.Users_Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBookRepository extends JpaRepository<Users_Books, Users_Books.UserBookId> {

}
