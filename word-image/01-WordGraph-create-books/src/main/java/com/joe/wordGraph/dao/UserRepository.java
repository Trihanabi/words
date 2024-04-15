package com.joe.wordGraph.dao;

import com.joe.wordGraph.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}