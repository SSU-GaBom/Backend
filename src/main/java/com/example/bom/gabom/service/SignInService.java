package com.example.bom.gabom.service;

import com.example.bom.gabom.model.dto.User;
import com.example.bom.gabom.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;

@Service
@RequiredArgsConstructor
public class SignInService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User signIn(String id, String passwd){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = null;

        System.out.println(userRepository.findByUserId(id).getUserPw());

        if(passwordEncoder.matches(userRepository.findByUserId(id).getUserPw(), passwd)){
            System.out.println("반환됐어용.");
            user = userRepository.findByUserId(id);
        }
        return user;
    }
}
