package com.example.bom.gabom.configuration.security;

import com.example.bom.gabom.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginIdPwValidator implements UserDetailsService {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException {
        com.example.bom.gabom.model.entity.User user = userRepository.findByUserId(insertedId);

        if (user == null) {
            return null;
        }

        String pw = user.getUserPw(); //"d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db"
        String Auth = user.getUserAuth(); //"USER"

        return User.builder()
                .username(insertedId)
                .password(pw)
                .roles(Auth)
                .build();
    }
}