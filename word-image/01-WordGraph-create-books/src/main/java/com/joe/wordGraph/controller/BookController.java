package com.joe.wordGraph.controller;

import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.service.BookService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService theBookService) {
        bookService = theBookService;
    }

    // add mapping for "/list"

    @GetMapping("/books")
    public String listBooks(Model theModel) {

        // get the books from db
        List<Book> theBooks = bookService.findAll();

        // add to the spring model
        theModel.addAttribute("books", theBooks);

        return "books/list_books";
    }

    @GetMapping("/books/{bookId}")
    public String showBookWord(Model theModel, @PathVariable int bookId) {

        // get the books from db
        Book theBook = bookService.findById(bookId);
        theModel.addAttribute("book", theBook);

        // add to the spring model

        return "books/book_words";
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
