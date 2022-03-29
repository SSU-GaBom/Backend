package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.model.dto.UserDto;
import com.example.bom.gabom.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final AuthMailService authMailService;

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

    //이메일인증 하는데 있어 난수를 메일로 보내는 메소드
    @Transactional
    public Boolean sendEmail(String email, HttpSession session) {
        boolean sendable = true;
        Random r = new Random();
        Integer randomnum = r.nextInt(1000000);
        Integer statusnum = 0;

        session.setAttribute(email, randomnum.toString());
        return authMailService.sendMail(sendable, email, statusnum, randomnum);
    }

    //
    @Transactional
    public Boolean authMail(String randnum, String email, HttpSession session){
        Object sessionrandnum = session.getAttribute(email);

        //입력한 난수와 세션에 저장되어있는 난수가 같다면 이메일 인증에 성공한 것이므로 다음단계로 넘어갈 수 있음.
        if (sessionrandnum.equals(randnum))
            return true;

        return false;
    }

    @Transactional
    public void joinUser(UserDto userDto) {
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
