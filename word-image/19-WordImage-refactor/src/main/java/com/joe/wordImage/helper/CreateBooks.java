package com.joe.wordImage.helper;

import com.alibaba.fastjson.JSON;
import com.joe.wordImage.dao.BookRepository;
import com.joe.wordImage.dao.WordRepository;
import com.joe.wordImage.entity.Book;
import com.joe.wordImage.entity.Word;
import com.joe.wordImage.service.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.io.File;
import java.nio.file.Files;
import java.util.*;


// It is used for create books data and store in DB
// @Component
@Order(1)
@Slf4j
public class CreateBooks implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private BookService bookService = new BookServiceImpl(bookRepository);

    @Autowired
    private WordService wordService = new WordServiceImpl(wordRepository);


    private Map<String, Pair<Integer, String>> wordMap = new HashMap<>();
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

            // Insert book object to MySQL
            if (!bookService.isContainBook(fileName)) {
                Book book = createBook(fileName, bookWordText);
                bookService.save(book);
            } else {
//                // 更新已有书籍单词列表
//                System.out.println("updating the book " + fileName + "...");
//                updateBook(fileName, bookWordText);
//                System.out.println("updated the book " + fileName);
            }
            

        }
        System.out.println("created all book ");
    }

    public void updateBook(String bookName, String bookWordText){
        // build book word json string
        String jsonString = buildBookJsonStr(bookName, bookWordText);

        // update book
        Book book = bookService.findByName(bookName);
        book.setWordList(jsonString);
        bookService.save(book);
    }

    // use json array objects to build book object
    public Book createBook(String bookName, String bookWordText) {
        // build book word json string
        String jsonString = buildBookJsonStr(bookName, bookWordText);
        return new Book(bookName, jsonString);
    }

    public String buildBookJsonStr(String fileName, String bookWordText) {
        String jsonString;
        // build word list(json string)
        String[] lines = bookWordText.split("\n");
        JSONArray jsonArr = new JSONArray();
        for (String line: lines) {
            String[] strs = line.split("\\s+");
            if(strs.length != 2) {
                continue;
            }
            String word = strs[0];
            int occurence_freqence = Integer.parseInt(strs[1]);
            if (!wordMap.containsKey(word)) {
                searchWord(word);
            }
            var wordItem = wordMap.get(word);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("word", word);
            jsonObj.put("occurence_freqence", occurence_freqence);

            // 如果总单词表中没有此单词，则不添加
            if (wordItem != null) {
                Integer id = wordItem.getFirst();
                String chinese = wordItem.getSecond();
                jsonObj.put("chinese", chinese);
                jsonObj.put("id", id);
            }
            jsonArr.put(jsonObj);
        }
        System.out.println(jsonArr.get(0));

        jsonString = JSON.toJSONString(jsonArr);
        return jsonString;
    }

    public void searchWord(String word) {
        // 模糊查询
        Word searchedWord = wordService.fuzzyFindByWord(word);
        if (searchedWord != null) {
            Integer id = searchedWord.getId();
            String chinese = searchedWord.getChinese();
            Pair<Integer, String> wordInfo = new Pair<>(id, chinese);
            // 更新hashmap，将结构存储到hashmap中用于之后的更新工作
            wordMap.put(searchedWord.getWord(), wordInfo);
        }
    }

    public class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }


}