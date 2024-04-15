package com.joe.wordGraph.service;

import com.joe.wordGraph.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    User findById(int theId);

    User findFirstByName(String name);

    void save(User theUser);

    void deleteById(int theId);

    boolean isContainName(String name);
}