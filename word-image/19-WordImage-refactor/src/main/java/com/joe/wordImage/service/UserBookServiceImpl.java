package com.joe.wordImage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.wordImage.dao.UserBookRepository;
import com.joe.wordImage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;


@Service
public class UserBookServiceImpl implements UserBookService {

    private UserBookRepository userBookRepository;
    private UserWord word;
    private String graphGalleryPath = "C:\\Projects\\WordGraphs\\PhotoGallery\\";
    @Autowired
    public UserBookServiceImpl(UserBookRepository theUserBookRepository) {
        userBookRepository = theUserBookRepository;
    }

    @Override
    public List<Users_Books> findAll() {
        return userBookRepository.findAll();
    }

    @Override
    public Users_Books findById(Users_Books.UserBookId theId) {
        Optional<Users_Books> result = userBookRepository.findById(theId);

        Users_Books theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the userBook
            throw new RuntimeException("Did not find user_book by id - " + theId);
        }

        return theUser;
    }

    @Override
    public void save(Users_Books theUserBook) {
        userBookRepository.save(theUserBook);
    }

    @Override
    public void deleteById(Users_Books.UserBookId theId) {
        userBookRepository.deleteById(theId);
    }

    /**
     * 为了给UserBook设置新的UserWordList
     * @param theUserWordList: 新的UserWordList
     * @param theUserBook: Users_Books 关系表
     * @throws JsonProcessingException
     */
    @Override
    public void setUserWordList(UserWordList theUserWordList, Users_Books theUserBook) throws JsonProcessingException {
        List<UserWord> wordList = theUserWordList.getWords();
        // 将 List<UserWord> 转换为json string来存储到数据库
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(wordList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        theUserBook.setUserWordList(jsonString);
        userBookRepository.save(theUserBook);
    }


    // 通过用户id和书籍id获取单词列表
    @Override
    public UserWordList getUserWordList(int userId, int bookId) {
        Users_Books usersBooks = findById(new Users_Books.UserBookId(userId, bookId));
        UserWordList theUserWordList = new UserWordList();

        if (usersBooks != null) {
            String userWordList = usersBooks.getUserWordList();
            theUserWordList.setWords(createUserWordList(userWordList));
        } else {
            // we didn't find the userBook
            throw new RuntimeException("Did not find user_book by id - " + userId + "&" +  bookId);
        }
        return theUserWordList;
    }

    // convert JSON string to List<UserWord>
    public List<UserWord> createUserWordList(String userWordListStr) {

        List<UserWord> userWordList;
        Type listType = new TypeToken<List<UserWord>>() {}.getType();
        userWordList = new Gson().fromJson(userWordListStr, listType);
        return userWordList;

    }


    // 用List<UserWord>创建UserwordList
    @Override
    public UserWordList buildUserWordList(WordList theWordList) {
        UserWordList theUserWordList = new UserWordList();
        for (var str: theWordList.getWordsAndOcc()) {
            String[] strs = str.split(":");
            String word = strs[0];
            int times = Integer.valueOf(strs[1]);
            String chinese = strs[2];
            UserWord userWord = new UserWord(word, times, chinese);
            theUserWordList.addWord(userWord);
        }
        return theUserWordList;
    }


}
