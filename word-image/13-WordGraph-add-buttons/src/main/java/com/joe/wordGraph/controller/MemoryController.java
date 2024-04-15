package com.joe.wordGraph.controller;


import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.Graph;
import com.joe.wordGraph.entity.Users_Books;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.UserBookService;
import com.joe.wordGraph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/memory")
public class MemoryController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
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

        List<String> graphsUrl = graphs.stream()
                        .map(graph -> graph.getName() + ".jpg")
                        .collect(Collectors.toList());

        theModel.addAttribute("graphs", graphsUrl);

        return "memory/word_memory";
    }

}
