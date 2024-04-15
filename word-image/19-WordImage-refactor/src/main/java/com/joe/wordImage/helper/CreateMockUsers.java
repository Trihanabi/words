package com.joe.wordImage.helper;

import com.joe.wordImage.dao.UserRepository;
import com.joe.wordImage.entity.User;
import com.joe.wordImage.service.UserService;
import com.joe.wordImage.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;


// It is used for build a mock user joe
//@Component
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
        if (!userService.isContainUser(mockUser.getName())) {
            userService.save(mockUser);
        }
    }
}
