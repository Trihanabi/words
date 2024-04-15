package com.joe.wordGraph.dao;

import com.joe.wordGraph.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphRepository extends JpaRepository<Graph, Integer> {

}