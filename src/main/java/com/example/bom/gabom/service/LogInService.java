package com.example.bom.gabom.service;

import com.example.bom.gabom.etc.SessionConstraints;
import com.example.bom.gabom.model.dto.LoginDto;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LogInService {

    private final UserRepository userRepository;

    @Transactional
    public User signIn(LoginDto loginDto, HttpSession session){

        //입력받은 아이디와 비밀번호
        String loginid = loginDto.getLoginId();
        String passwd = loginDto.getLoginPw();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = null;

        if(passwordEncoder.matches(userRepository.findByUserId(loginid).getUserPw(), passwd)){
            user = userRepository.findByUserId(loginid);

            createSession(user, session);
        }
        return user;
    }

    public void createSession(User user, HttpSession session){
        session.setAttribute(SessionConstraints.Login_User, user);
    }
}
