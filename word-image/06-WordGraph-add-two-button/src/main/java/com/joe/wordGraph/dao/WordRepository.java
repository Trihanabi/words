package com.joe.wordGraph.dao;

import com.joe.wordGraph.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {

}