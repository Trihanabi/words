package com.joe.wordGraph.controller;


import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.Graph;
import com.joe.wordGraph.entity.Users_Books;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.UserBookService;
import com.joe.wordGraph.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/memory")
public class MemoryController {

    private BookService bookService;
    private UserService userService;
    private UserBookService userBookService;

    public MemoryController(BookService theBookService,
                          UserService theUserService,
                          UserBookService theUserBookService) {

        bookService = theBookService;

        userService = theUserService;

        userBookService = theUserBookService;
    }

    @GetMapping("/{userId}&{bookId}")
    public String startMemory(Model theModel,
                              @PathVariable int userId,
                              @PathVariable int bookId ) {
        List<Graph> graphs = userBookService.getGraphs(userId, bookId);

        theModel.addAttribute("graphs", graphs);

        return "memory/word_memory";
    }
}
