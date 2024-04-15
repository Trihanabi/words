package com.joe.wordGraph.service;

import com.joe.wordGraph.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserBookService {

    List<Users_Books> findAll();

    Users_Books findById(Users_Books.UserBookId theId);

    void save(Users_Books theUserBook);

    void deleteById(Users_Books.UserBookId theId);

    void setUserWordList(UserWordList theUserWordList, Users_Books theUserBook) throws IllegalAccessException;


    UserWordList getUserWordList(int userId, int bookId);

    // use word get 9 graph
    List<Graph> getNineGraph(UserWord userWord);

    UserWordList buildUserWordList(WordList theWordList);

    List<Graph> getGraphs(int userId, int bookId);

    // check the graph is correct


    // set UserWordList




}
