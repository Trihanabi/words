package com.joe.wordImage.dao;

import com.joe.wordImage.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    Word findFirstByWord(String word);

}