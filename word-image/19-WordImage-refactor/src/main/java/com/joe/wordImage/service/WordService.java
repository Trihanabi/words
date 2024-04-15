package com.joe.wordImage.service;

import com.joe.wordImage.entity.Word;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WordService {

    List<Word> findAll();

    Word findById(int theId);

    void save(Word theWord);

    void deleteById(int theId);

    /**
     * 模糊查询单词
     */
    Word fuzzyFindByWord(String word);
}
