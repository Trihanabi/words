package com.joe.wordGraph.controller;

import com.joe.wordGraph.entity.*;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.UserBookService;
import com.joe.wordGraph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
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
    public String listBooks(Model theModel) {

        // get the books from db
        List<Book> theBooks = bookService.findAll();

        // add to the spring model
        theModel.addAttribute("books", theBooks);

        return "books/list_books";
    }


    @GetMapping("/{bookId}")
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
    public String createUserWordList(@ModelAttribute("wordList") WordList theWordList
                                    , @PathVariable int bookId) throws IllegalAccessException {
        // log the input data
//        System.out.println(theWordList);

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
    public String submit(@PathVariable int userId,
                         @PathVariable int bookId ) {
        Users_Books.UserBookId userBookId = new Users_Books.UserBookId(userId, bookId);

        Users_Books theUserBook = userBookService.findById(userBookId);

//        System.out.println(theUserBook + "has saved!!");

        return "books/submit-words";
    }
}
