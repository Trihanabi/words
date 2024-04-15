package com.joe.wordGraph.controller;

import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.UserWordList;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserBookService userBookService;

    public DataController(BookService theBookService,
                          UserBookService theUserBookService) {
        bookService = theBookService;

        userBookService = theUserBookService;
    }

    @GetMapping("/{userId}&{bookId}")
    public ResponseEntity<Object> getUserWordListData(@PathVariable int userId,
                                          @PathVariable int bookId ) {
        // 处理获取数据的逻辑
        UserWordList userWordList = userBookService.getUserWordList(userId, bookId);

        // 返回数据作为响应
        return ResponseEntity.ok(userWordList.getWords());
    }

    @GetMapping("/graphs/{bookId}")
    public ResponseEntity<Object> getGraphsStrData(@PathVariable int bookId ) {
        // 处理获取数据的逻辑
        Book theBook = bookService.findById(bookId);

        List<String> graphNameList = bookService.getGraphStrs(theBook);
        // 返回数据作为响应
        return ResponseEntity.ok(graphNameList);
    }
}