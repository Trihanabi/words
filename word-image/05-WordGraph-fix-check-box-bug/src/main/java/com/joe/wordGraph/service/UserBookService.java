package com.joe.wordGraph.service;

import com.joe.wordGraph.entity.User;
import com.joe.wordGraph.entity.UserWordList;
import com.joe.wordGraph.entity.Users_Books;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserBookService {

    List<Users_Books> findAll();

    Users_Books findById(Users_Books.UserBookId theId);

    void save(Users_Books theUserBook);

    void deleteById(Users_Books.UserBookId theId);

    void setUserWordList(UserWordList theUserWordList, Users_Books theUserBook);
}
