package com.joe.wordGraph.controller;

import com.joe.wordGraph.entity.*;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.UserBookService;
import com.joe.wordGraph.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    private UserService userService;

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

        UserWordList userWordList = new UserWordList();

        theModel.addAttribute("userWordList", userWordList);

        return "books/book_words";
    }


    @PostMapping("/createUserWordList/{bookId}")
    public String createUserWordList(@ModelAttribute("userWordList") UserWordList theUserWordList
                                    , @PathVariable int bookId) {
        // log the input data
        System.out.println(theUserWordList);
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

//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model theModel) {
//
//        // create model attribute to bind form data
//        Book theBook = new Book();
//
//        theModel.addAttribute("book", theBook);
//
//        return "books/book-form";
//    }
//
//    @PostMapping("/showFormForUpdate")
//    public String showFormForUpdate(@RequestParam("bookId") int theId,
//                                    Model theModel) {
//
//        // get the book from the service
//        Book theBook = bookService.findById(theId);
//
//        // set book as a model attribute to pre-populate the form
//        theModel.addAttribute("book", theBook);
//
//        // send over to our form
//        return "books/book-form";
//    }
//
//    @PostMapping("/save")
//    public String saveBook(@ModelAttribute("book") Book theBook) {
//
//        // save the book
//        bookService.save(theBook);
//
//        // use a redirect to prevent duplicate submissions
//        return "redirect:/books/list";
//    }
//
//    @PostMapping("/delete")
//    public String delete(@RequestParam("bookId") int theId) {
//
//        // delete the book
//        bookService.deleteById(theId);
//
//        // redirect to /books/list
//        return "redirect:/books/list";
//
//    }
}
