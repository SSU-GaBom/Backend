package com.example.bom.gabom.service;

import com.example.bom.gabom.advice.exception.CUserNotFoundException;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public User showInfo(String userId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        return new User();
    }
}
