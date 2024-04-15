package com.joe.wordImage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joe.wordImage.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserBookService {

    List<Users_Books> findAll();

    Users_Books findById(Users_Books.UserBookId theId);

    void save(Users_Books theUserBook);

    void deleteById(Users_Books.UserBookId theId);

    /**
     * 给user_book关系表中的对象设置新的单词列表
     */
    void setUserWordList(UserWordList theUserWordList, Users_Books theUserBook) throws IllegalAccessException, JsonProcessingException;

    /**
     * 通过用户id和书籍id获取用户单词列表
     * @return
     */
    UserWordList getUserWordList(int userId, int bookId);

    /**
     * 用List<UserWord>创建UserwordList，用于数据的转换
     * @param theWordList 通过用户选择构成的单词列表
     * @return
     */
    UserWordList buildUserWordList(WordList theWordList);


}
