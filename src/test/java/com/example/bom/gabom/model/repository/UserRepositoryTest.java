package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void UserRepositoryTest() {
        // given
        User user = new User(30L,"fish","123","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
        userRepository.save(user);
        // when
        User sion = userRepository.findByUserName("sion");
        // then
        Assertions.assertThat(sion.getUserId()).isEqualTo("fish");
    }
}