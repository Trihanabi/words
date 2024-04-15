package com.joe.wordImage.service;

import java.io.IOException;
import java.util.List;

import com.joe.wordImage.entity.Book;
import com.joe.wordImage.entity.BookWord;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    List<Book> findAll();

    Book findById(int theId);

    void save(Book theBook);

    void deleteById(int theId);

    /**
     * 检查数据库中是否包含书籍
     * @param name 书籍名
     * @return
     */
    boolean isContainBook(String name);

    /**
     * 创建书籍单词列表
     * @param theBook
     * @return
     */
    List<BookWord> createBookWordList(Book theBook);

    /**
     * 返回书籍图片名集合
     * @param theBook
     * @return
     */
    List<String> getGraphStrs(Book theBook) throws IOException;

    void updateWordList(Book theBook, String wordList);

    /**
     * 得到数据库中第一个与之匹配的书籍
     * @param bookName
     * @return
     */
    Book findByName(String bookName);
}
