package com.joe.wordGraph.controller;

import com.joe.wordGraph.entity.UserWordList;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.UserBookService;
import com.joe.wordGraph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private UserBookService userBookService;

    public DataController(UserBookService theUserBookService) {
        userBookService = theUserBookService;
    }
    @GetMapping("/{userId}&{bookId}")
    public ResponseEntity<Object> getData(@PathVariable int userId,
                                          @PathVariable int bookId ) {
        // 处理获取数据的逻辑
        UserWordList userWordList = userBookService.getUserWordList(userId, bookId);

        // 返回数据作为响应
        return ResponseEntity.ok(userWordList);
    }
}