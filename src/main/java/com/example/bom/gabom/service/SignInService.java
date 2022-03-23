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

        //match의 parameter가 앞쪽이 raw타입, 뒤쪽이 encoded 타입이어야함.
        if(passwordEncoder.matches(passwd, userRepository.findByUserId(id).getUserPw())){
            System.out.println("반환됐어용.");
            user = userRepository.findByUserId(id);
        }
        return user;
    }
}
