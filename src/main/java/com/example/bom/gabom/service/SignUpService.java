package com.example.bom.gabom.service;

import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.dto.UserDto;
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
