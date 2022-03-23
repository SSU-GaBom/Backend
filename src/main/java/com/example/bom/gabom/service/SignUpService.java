package com.example.bom.gabom.service;

import com.example.bom.gabom.model.dto.User;
import com.example.bom.gabom.model.vo.UserDto;
import com.example.bom.gabom.model.repository.UserRepository;
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
    public void joinUser(UserDto userDto){
        User user = new User();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        userDto.setUserAuth("User");
        userDto.setAppendDate(localTime);
        userDto.setUpdateDate(localTime);
        user.setUserField(userDto);
        userRepository.save(user);
    }
}
