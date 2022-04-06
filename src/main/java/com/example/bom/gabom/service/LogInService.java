package com.example.bom.gabom.service;

import com.example.bom.gabom.advice.exception.CEmailAuthTokenNotFoundException;
import com.example.bom.gabom.advice.exception.CEmailSigninFailedException;
import com.example.bom.gabom.configuration.security.JwtTokenProvider;
import com.example.bom.gabom.dto.LoginDto;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.model.response.SingleResult;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LogInService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider; // jwt 토큰 생성
    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    @Transactional
    public SingleResult<String> signIn(LoginDto loginDto){

        //입력받은 아이디와 비밀번호
        String id = loginDto.getLoginId();
        String password = loginDto.getLoginPw();

        User user = userRepository.findByUserId(id).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            // matches : 평문, 암호문 패스워드 비교 후 boolean 결과 return
            throw new CEmailSigninFailedException();
        }
        if(user.getEmailAuth() == false){
            throw new CEmailAuthTokenNotFoundException();
        }
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getUserId()), user.getRoles()));
    }
}
