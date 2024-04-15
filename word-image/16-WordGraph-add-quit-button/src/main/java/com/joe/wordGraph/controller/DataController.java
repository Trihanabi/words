package com.joe.wordGraph.controller;

import com.joe.wordGraph.entity.*;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @PostMapping("/save/userWordList/{userId}&{bookId}")
    public ResponseEntity<?> saveWordData(@RequestBody UserWord[] userWordList,
                                            @PathVariable int userId,
                                            @PathVariable int bookId) throws IllegalAccessException {
//        System.out.println( userWordList);
        // 对接收到数据进行验证
        if (!validateWordData(userWordList)) {
            // 返回自定义的错误响应
            return ResponseEntity.badRequest().body("Invalid form data");
        }

        // 执行数据持久化操作
        if (!persistFormData( userWordList, userId, bookId)) {
            // 返回服务器错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to persist form data");
        }

        System.out.println("UserId:"+ userId + ", BookId:"+bookId +", Data received successfully");

        // 返回成功的响应
        return ResponseEntity.ok("Data received successfully");
    }

    private boolean validateWordData(UserWord[] userWordList) {

        if (userWordList == null) {
            return false;
        }

        return true;
    }

    private boolean persistFormData(UserWord[] userWordList, int userId,
                                    int bookId) throws IllegalAccessException {
        // 进行数据验证、持久化等操作
        try {
            Users_Books userbook = userBookService.findById(new Users_Books.UserBookId(userId, bookId));
            UserWordList newUserWordList = new UserWordList(Arrays.asList(userWordList));

            userBookService.setUserWordList(newUserWordList, userbook);
            return true;
        } catch (Exception e) {
            // 持久化过程中发生异常
            e.printStackTrace();
            return false;
        }
    }
}