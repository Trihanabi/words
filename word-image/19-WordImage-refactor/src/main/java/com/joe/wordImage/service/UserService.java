package com.joe.wordImage.service;

import com.joe.wordImage.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    User findById(int theId);

    User findFirstByName(String name);

    void save(User theUser);

    void deleteById(int theId);

    boolean isContainUser(String name);
}