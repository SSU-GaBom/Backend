package com.example.bom.gabom.service;

import com.example.bom.gabom.dto.UserDto;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    @Transactional
    public boolean checkId(String userId) {
        //아이디가 존재하면 생성 불가
        if (userRepository.existsByUserId(userId))
            return false;
        return true;
    }

    //이메일이 존재하는지 확인정도의 서비스
    @Transactional
    public Boolean checkEmail(String email) {
        if (userRepository.existsByEmail(email))
            return false;
        return true;
    }


    @Transactional
    public void joinUser(UserDto userDto) {
        userRepository.save(User.builder()
                .userId(userDto.getUserId())
                .userPw(passwordEncoder.encode(userDto.getUserPw()))
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .nickName(userDto.getNickName())
                .appendDate(localTime)
                .updateDate(localTime)
                .provider(null)
                .emailAuth(false)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }
}
