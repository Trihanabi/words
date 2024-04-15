package com.joe.wordImage.dao;

import com.joe.wordImage.entity.Users_Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookRepository extends JpaRepository<Users_Books, Users_Books.UserBookId> {

}
