package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class FindUserService {

    private final UserRepository userRepository;
    private final MailAuthService mailAuthService;

    public User findId(String email, String name, HttpSession session){
        User user = userRepository.findByEmailAndUserName(email, name);

        mailAuthService.mailAuth(user, email, name, 2,session);

        return user;
    }
}
