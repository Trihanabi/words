package com.joe.wordGraph.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.wordGraph.dao.BookRepository;
import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.BookWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository) {
        bookRepository = theBookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        Book theBook = null;

        if (result.isPresent()) {
            theBook = result.get();
        }
        else {
            // we didn't find the book
            throw new RuntimeException("Did not find book id - " + theId);
        }

        return theBook;
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }

    @Override
    @Query("SELECT t FROM books t WHERE t.name = ?")
    public boolean isContainName(String name) {
        if (bookRepository.findByName(name).isEmpty()) {
            return false;
        }
        return true;
    }

    public List<BookWord> createBookWordList(Book theBook) {
        String wordListStr = theBook.getWordList();

        List<BookWord> bookWordList;
        Type listType = new TypeToken<List<BookWord>>() {}.getType();
        bookWordList = new Gson().fromJson(wordListStr, listType);

        return bookWordList;

    }

}
