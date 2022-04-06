package com.example.bom.gabom.service;

import com.example.bom.gabom.advice.exception.EmailAuthTokenNotFountException;
import com.example.bom.gabom.advice.exception.UserEmailAlreadyExistsException;
import com.example.bom.gabom.advice.exception.UserNotFoundException;
import com.example.bom.gabom.entity.EmailAuth;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.dto.UserDto;
import com.example.bom.gabom.repository.EmailAuthRepository;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final EmailAuthRepository emailAuthRepository;
    private final AuthMailService authMailService;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화
    private final EmailService emailService;

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
        Integer randnum = r.nextInt(1000000);
        Integer statusnum = 0;

        session.setAttribute(email, randnum.toString());
        return authMailService.sendMail(sendable, email, statusnum, randnum);
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

    @Transactional
    public userRegisterResponseDto registeruser(userRegisterRequestDto requestDto) {
        validateDuplicated(requestDto.getEmail());
        EmailAuth emailAuth = emailAuthRepository.save(
                EmailAuth.builder()
                        .email(requestDto.getEmail())
                        .authToken(UUID.randomUUID().toString())
                        .expired(false)
                        .build());

        emailService.send(emailAuth.getEmail(), emailAuth.getAuthToken());
        return userRegisterResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .authToken(emailAuth.getAuthToken())
                .build();
    }

    public void validateDuplicated(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistsException();
    }
    
    @Transactional
    public void confirmEmail(EmailAuthRequestDto requestDto) {
        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(requestDto.getEmail(), requestDto.getAuthToken(), LocalDateTime.now())
                .orElseThrow(EmailAuthTokenNotFountException::new);
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(UserNotFoundException::new);
        emailAuth.useToken();
        user.emailVerifiedSuccess();
    }
}
