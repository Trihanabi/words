package com.joe.wordImage.controller;

import com.joe.wordImage.entity.*;
import com.joe.wordImage.service.BookService;
import com.joe.wordImage.service.UserBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/data")
@Api(value = "数据控制器")
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
    @ApiOperation(value = "获取相应的单词列表")
    public ResponseEntity<Object> getUserWordListData(@PathVariable int userId,
                                          @PathVariable int bookId ) {
        // 处理获取数据的逻辑
        UserWordList userWordList = userBookService.getUserWordList(userId, bookId);

        // 返回数据作为响应
        return ResponseEntity.ok(userWordList.getWords());
    }

    @GetMapping("/graphs/{bookId}")
    @ApiOperation(value = "获取相应的书籍的图片名集合")
    public ResponseEntity<Object> getGraphsStrData(@PathVariable int bookId ) throws IOException {
        // 处理获取数据的逻辑
        Book theBook = bookService.findById(bookId);

        List<String> graphNameList = bookService.getGraphStrs(theBook);
        // 返回数据作为响应
        return ResponseEntity.ok(graphNameList);
    }

    @PostMapping("/save/userWordList/{userId}&{bookId}")
    @ApiOperation(value = "存储记忆完的单词列表")
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

    // 验证单词列表是否为有效数据，检查是否为空
    private boolean validateWordData(UserWord[] userWordList) {

        if (userWordList == null) {
            return false;
        }

        return true;
    }

    // 持久化单词列表
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