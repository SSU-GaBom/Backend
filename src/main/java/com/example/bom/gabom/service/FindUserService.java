package com.example.bom.gabom.service;

import com.example.bom.gabom.advice.exception.CUserNotFoundException;
import com.example.bom.gabom.dto.FindUserDto;
import com.example.bom.gabom.dto.UserAuthDto;
import com.example.bom.gabom.entity.ConfirmationToken;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.model.response.CommonResult;
import com.example.bom.gabom.model.response.SingleResult;
import com.example.bom.gabom.repository.ConfirmationTokenRepository;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FindUserService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final CheckService checkService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService; // API 요청 결과에 대한 code, message

    //return true면 유저가 존재해서 정상적으로 메일이 보내진거 false면 유저가 없거나 메일이 보내지지 않은 것
    @Transactional
    public String findId(FindUserDto findUserDto){
        if(checkService.checkUserNameAndEmail(findUserDto.getUserName(), findUserDto.getEmail()))
            throw new CUserNotFoundException();
        return userRepository.findByUserNameAndEmail(findUserDto.getUserName(), findUserDto.getEmail()).orElseThrow().getUserId();
    }

    @Transactional
    public CommonResult findPassword(FindUserDto findUserDto){
        if(checkService.checkUserIdAndEmail(findUserDto.getUserId(), findUserDto.getEmail()))
            throw new CUserNotFoundException();
        sendAuthMail(findUserDto);
        return responseService.getSuccessResult();
        //return userRepository.findByUserIdAndEmail(findUserDto.getUserId(), findUserDto.getEmail()).orElseThrow().getUserId();
    }

    @Transactional
    public String sendAuthMail(FindUserDto findUserDto){
        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(findUserDto.getUserId());
        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("springgabom@gmail.com");
        mailMessage.setTo(findUserDto.getEmail());
        mailMessage.setSubject("회원가입 이메일 인증");

        //http://localhost:8080/changepw?token=~~으로 들어가면 비밀번호 받는 페이지 있고 해당 페이지에서
        // 로그인 버튼 누르면 http://localhost:8080/finduser/changepw?token=으로 넘어가는 방식?
        mailMessage.setText("http://localhost:8080/finduser/changepw?token="+emailConfirmationToken.getId());
        emailService.send(mailMessage);

        return emailConfirmationToken.getId();
    }

    //넘겨 받은 객체로 이메일을 통해 유저 정보를 얻어서 비밀번호를 업데이트한다.
    @Transactional
    public boolean setPassword(String password, User user){
        userRepository.updatePassWord(passwordEncoder.encode(password), user.getUserNo());

        if(passwordEncoder.matches(password, user.getUserPw()))
            return true;

        return false;
    }
}
