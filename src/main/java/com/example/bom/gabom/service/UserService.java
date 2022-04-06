package com.example.bom.gabom.service;

import com.example.bom.gabom.entity.ConfirmationToken;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UserService  {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    /**
     * 이메일 인증 로직
     * @param token
     */
    public void confirmEmail(String token) {
        ConfirmationToken findConfirmationToken = confirmationTokenService.findByIdAndExpirationDateAfterAndExpired(token);
        User user = userRepository.findByUserId(findConfirmationToken.getUserId()).orElseThrow();
        findConfirmationToken.useToken();	// 토큰 만료 로직을 구현해주면 된다. ex) expired 값을 true로 변경
        user.emailVerifiedSuccess();	// 유저의 이메일 인증 값 변경 로직을 구현해주면 된다. ex) emailVerified 값을 true로 변경
    }
}