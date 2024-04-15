package com.joe.wordGraph.helper;

import com.joe.wordGraph.dao.UserRepository;
import com.joe.wordGraph.entity.User;
import com.joe.wordGraph.service.UserService;
import com.joe.wordGraph.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
// It is used for Advice execution precedence
@Order(2)
@Slf4j
public class CreateMockUsers implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService = new UserServiceImpl(userRepository) ;

    @Override
    public void run(String... args) throws Exception {
        User mockUser = new User("joe");
//        System.out.println(!userService.isContainName(mockUser.getName()));
        if (!userService.isContainName(mockUser.getName())) {
            userService.save(mockUser);
        }
    }
}
