package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    @Transactional
    public boolean checkId(String userId){
        //아이디가 존재하면 생성 불가
        if(userRepository.existsByUserId(userId))
            return true;

        return false;
    }

    @Transactional
    public void joinUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        user.setUserAuth("User");
        user.setAppendDate(localTime);
        user.setUpdateDate(localTime);
        userRepository.save(user);
    }
}
