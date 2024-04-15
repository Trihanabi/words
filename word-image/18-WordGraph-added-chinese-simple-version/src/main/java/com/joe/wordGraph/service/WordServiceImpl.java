package com.joe.wordGraph.service;

import com.joe.wordGraph.dao.BookRepository;
import com.joe.wordGraph.dao.WordRepository;
import com.joe.wordGraph.entity.Book;
import com.joe.wordGraph.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            // we didn't find the word
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

        if (resWord == null) {
            String fuzzySearchWord = clearSuffix(word, "s");
            resWord = wordRepository.findFirstByWord(fuzzySearchWord);
        }

        if (resWord == null) {
            String fuzzySearchWord = clearSuffix(word, "ed");
            resWord = wordRepository.findFirstByWord(fuzzySearchWord);
        }

        if (resWord == null) {
            String fuzzySearchWord = clearSuffix(word, "d");
            resWord = wordRepository.findFirstByWord(fuzzySearchWord);
        }

        if (resWord == null) {
            String fuzzySearchWord = clearSuffix(word, "ing");
            resWord = wordRepository.findFirstByWord(fuzzySearchWord);
        }
//        System.out.println(word + ": " + resWord);
        return resWord;
    }

    public String clearSuffix(String word, String suffix) {
        if (word.endsWith(suffix)) {
            int len = suffix.length();
            word = word.substring(0, word.length() - len);
        }
        return word;
    }
}
