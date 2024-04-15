package com.joe.wordImage.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.wordImage.dao.BookRepository;
import com.joe.wordImage.entity.Book;
import com.joe.wordImage.entity.BookWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    private ResourceLoader resourceLoader;

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
    public boolean isContainBook(String name) {
        if (bookRepository.findByName(name).isEmpty()) {
            return false;
        }
        return true;
    }

    // 创建一个书籍单词列表
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

    // 得到单词名集合
    @Override
    public List<String> getGraphStrs(Book theBook) throws IOException {
        List<BookWord> bookWordList = createBookWordList(theBook);
        if (bookWordList.size() > 500) {
            bookWordList = bookWordList.subList(0, 500);
        }
        List<String> graphNameList = bookWordList.stream()
                .map(bookWord -> bookWord.getWord() + ".webp")
                .collect(Collectors.toList());

        Set<String> set;
        int minNumSelected = 16;

        // 如果单词数小于最小图片数
        if (graphNameList.size() < minNumSelected) {
            set = new HashSet<>(graphNameList);
            Resource resource = resourceLoader.getResource("classpath:static/images/");
            URL graphGalleryURL = resource.getURL();
            File folder = new File(graphGalleryURL.getPath());
            File[] files = folder.listFiles();

            Random random = new Random();
            assert files != null;
            int numFiles = files.length;
            while (set.size() < minNumSelected) {
                int randomIndex = random.nextInt(numFiles);
                String fileName = files[randomIndex].getName();
                set.add(fileName);
            }
            graphNameList = new ArrayList<>(set);
        }
        return graphNameList;
    }

    @Override
    public void updateWordList(Book theBook, String wordList) {
        theBook.setWordList(wordList);
    }

    // 返回第一个书籍
    @Override
    public Book findByName(String bookName) {
        return bookRepository.findByName(bookName).get(0);
    }

}
