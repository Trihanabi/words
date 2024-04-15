package com.joe.wordGraph.service;

import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.Word;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WordService {

    List<Word> findAll();

    Word findById(int theId);

    void save(Word theWord);

    void deleteById(int theId);

    Word fuzzyFindByWord(String word);
}
