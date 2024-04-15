package com.joe.wordGraph.service;

import com.alibaba.fastjson.JSON;
import com.joe.wordGraph.dao.UserBookRepository;
import com.joe.wordGraph.entity.UserWordList;
import com.joe.wordGraph.entity.Users_Books;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBookServiceImpl implements UserBookService {

    private UserBookRepository userBookRepository;

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

    @Override
    public void setUserWordList(UserWordList theUserWordList, Users_Books theUserBook) {
        List<String> wordsName = theUserWordList.getWords();
        JSONArray jsonArr = new JSONArray();
        for (String wordName: wordsName) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("occurence_freqence", 100);
            jsonObj.put("word", wordName);
            jsonArr.put(jsonObj);
        }
        String jsonString = JSON.toJSONString(jsonArr);
        System.out.println(jsonString);
        theUserBook.setUserWordList(jsonString);
        System.out.println(theUserBook.getUserWordList());
        userBookRepository.save(theUserBook);
    }

}
