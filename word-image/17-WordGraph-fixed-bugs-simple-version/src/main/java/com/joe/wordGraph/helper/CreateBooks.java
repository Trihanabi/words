package com.joe.wordGraph.helper;

import com.alibaba.fastjson.JSON;
import com.joe.wordGraph.dao.BookRepository;
import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.service.BookService;
import com.joe.wordGraph.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Component
// It is used for Advice execution precedence
@Order(1)
@Slf4j
public class CreateBooks implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService = new BookServiceImpl(bookRepository) ;

    @Override
    public void run(String... args) throws Exception {
        // convert files to json array objects
        String rootPath = "C:\\Projects\\WordGraphs\\06-WordGraph-add-two-button\\src\\main\\java\\com\\joe\\wordGraph\\book_word_list";
        final File folder = new File(rootPath);
        final List<File> fileList = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
        for (var file: fileList) {
            String bookWordText = Files.readString(file.toPath());
            String fileName = file.getName();
            fileName = fileName.substring(0, fileName.length()-17);
            Book book = createBook(fileName, bookWordText);

            // Insert book object to MySQL
            if (!bookService.isContainName(fileName)) {
                bookService.save(book);
            }
        }
    }
    // use json array objects to build book object

    public static Book createBook(String fileName, String bookWordText) {
        String message = null;
        String[] lines = bookWordText.split("\n");
        JSONArray jsonArr = new JSONArray();
        for (String line: lines) {
            String[] strs = line.split("\\s+");
            if(strs.length != 2) {
                continue;
            }
            String word = strs[0];
            int occurence_freqence = Integer.parseInt(strs[1]);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("word", word);
            jsonObj.put("occurence_freqence", occurence_freqence);
            jsonArr.put(jsonObj);
        }

        String jsonString = JSON.toJSONString(jsonArr);
        return new Book(fileName, jsonString);
    }


}