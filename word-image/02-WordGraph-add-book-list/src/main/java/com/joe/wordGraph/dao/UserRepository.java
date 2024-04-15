package com.joe.wordGraph.dao;

import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findByName(String name);
}