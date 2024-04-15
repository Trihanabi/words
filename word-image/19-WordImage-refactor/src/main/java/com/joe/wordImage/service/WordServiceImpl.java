package com.joe.wordImage.service;

import com.joe.wordImage.dao.WordRepository;
import com.joe.wordImage.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class WordServiceImpl implements WordService{

    private WordRepository wordRepository;

    @Autowired
    public WordServiceImpl(WordRepository theWordRepository) {
        wordRepository = theWordRepository;
    }

    @Override
    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    @Override
    public Word findById(int theId) {
        Optional<Word> result = wordRepository.findById(theId);

        Word theWord = null;

        if (result.isPresent()) {
            theWord = result.get();
        }
        else {
            throw new RuntimeException("Did not find word id - " + theId);
        }

        return theWord;
    }

    @Override
    public void save(Word theWord) {
        wordRepository.save(theWord);
    }

    @Override
    public void deleteById(int theId) {
        wordRepository.deleteById(theId);
    }

    @Override
    // 模糊查询单词
    public Word fuzzyFindByWord(String word) {
        Word resWord = null;

        resWord = wordRepository.findFirstByWord(word);

        List<String> suffixList = new ArrayList<>(Arrays.asList("ed", "ing", "s", "d"));

        // 清除后缀检查
        for (String suffix: suffixList) {
            String fuzzySearchWord = clearSuffix(word, "s");
            resWord = wordRepository.findFirstByWord(fuzzySearchWord);
            if (resWord != null) {
                break;
            }
        }

        return resWord;
    }

    // 如果单词使用后缀，清除后缀
    public String clearSuffix(String word, String suffix) {
        if (word.endsWith(suffix)) {
            int len = suffix.length();
            word = word.substring(0, word.length() - len);
        }
        return word;
    }
}
