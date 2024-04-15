package com.joe.wordImage.service;

import com.joe.wordImage.dao.UserRepository;
import com.joe.wordImage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the user
            throw new RuntimeException("Did not find user id - " + theId);
        }

        return theUser;
    }

    @Override
    public User findFirstByName(String name) {
        List<User> result = userRepository.findByName(name);

        User theUser = null;

        if (!result.isEmpty()) {
            theUser = result.get(0);
        } else {
            // we didn't find the user
            throw new RuntimeException("Did not find user name - " + name);
        }

        return theUser;
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    @Query("SELECT u FROM users u WHERE u.name = ?")
    public boolean isContainUser(String name) {
        if (userRepository.findByName(name).isEmpty()) {
            return false;
        }
        return true;
    }

}
