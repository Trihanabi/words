package com.joe.wordImage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joe.wordImage.entity.*;
import com.joe.wordImage.service.BookService;
import com.joe.wordImage.service.UserBookService;
import com.joe.wordImage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@Api(value = "书籍控制器")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserBookService userBookService;

    public BookController(BookService theBookService,
                          UserService theUserService,
                          UserBookService theUserBookService) {

        bookService = theBookService;

        userService = theUserService;

        userBookService = theUserBookService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    @ApiOperation(value = "显示书籍列表")
    public String listBooks(Model theModel) {

        // get the books from db
        List<Book> theBooks = bookService.findAll();

        // add to the spring model
        theModel.addAttribute("books", theBooks);

        return "books/list_books";
    }


    @GetMapping("/{bookId}")
    @ApiOperation(value = "显示书中单词")
    public String showBookWord(Model theModel, @PathVariable int bookId) {

        // get the books from db
        Book theBook = bookService.findById(bookId);

        theModel.addAttribute("book", theBook);

        List<BookWord> bookWordList = bookService.createBookWordList(theBook);

        theModel.addAttribute("bookWordList", bookWordList);

        WordList wordList = new WordList();

        theModel.addAttribute("wordList", wordList);

        return "books/book_words";
    }


    @PostMapping("/createUserWordList/{bookId}")
    @ApiOperation(value = "创建用户单词列表")
    public String createUserWordList(@ModelAttribute("wordList") WordList theWordList
                                    , @PathVariable int bookId) throws IllegalAccessException, JsonProcessingException {

        UserWordList theUserWordList = userBookService.buildUserWordList(theWordList);
        // use a mock user joe
        User theUser = userService.findFirstByName("joe");

        Book theBook = bookService.findById(bookId);

        Users_Books theUserBook = new Users_Books(theUser, theBook);

        Users_Books.UserBookId userbookId = theUserBook.getId();

        userBookService.setUserWordList(theUserWordList, theUserBook);

        System.out.println(userbookId);

        String userId = Integer.toString(theUser.getId());

        return "redirect:/books/submit/" + userId + "&{bookId}";
    }

    @GetMapping("/submit/{userId}&{bookId}")
    @ApiOperation(value = "提交单词")
    public String submit(@PathVariable int userId,
                         @PathVariable int bookId ) {

        return "books/submit-words";
    }
}
