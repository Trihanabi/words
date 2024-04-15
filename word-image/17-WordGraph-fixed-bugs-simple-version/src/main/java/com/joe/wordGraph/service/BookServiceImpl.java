package com.joe.wordGraph.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.wordGraph.dao.BookRepository;
import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.BookWord;
import com.joe.wordGraph.entity.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private String graphGalleryPath = "C:\\Projects\\WordGraphs\\16-WordGraph-add-quit-button\\src\\main\\resources\\static\\images";

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
        // 控制在500个之内
        if (bookWordList.size() > 500) {
            bookWordList = bookWordList.subList(0, 500);
        }
        return bookWordList;

    }

    @Override
    public List<String> getGraphStrs(Book theBook) {
        List<BookWord> bookWordList = createBookWordList(theBook);
        if (bookWordList.size() > 500) {
            bookWordList = bookWordList.subList(0, 500);
        }
        List<String> graphNameList = bookWordList.stream()
                .map(bookWord -> bookWord.getWord() + ".webp")
                .collect(Collectors.toList());

        Set<String> set;
        int numSelected = 16;
        if (graphNameList.size() < numSelected) {
            set = new HashSet<>(graphNameList);
            File folder = new File(graphGalleryPath);
            File[] files = folder.listFiles();

            Random random = new Random();
            assert files != null;
            int numFiles = files.length;
            while (set.size() < numSelected) {
                int randomIndex = random.nextInt(numFiles);
                String fileName = files[randomIndex].getName();
                set.add(fileName);
            }
            graphNameList = new ArrayList<>(set);
        }
        return graphNameList;
    }

}
