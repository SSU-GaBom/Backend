package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SignUpServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void LoginTest() {
        // given
        User user = new User();
        System.out.println("user = " + user);
        // when

        // then
    }
}