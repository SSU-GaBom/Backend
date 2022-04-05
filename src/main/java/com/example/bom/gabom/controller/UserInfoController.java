package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserRepository userRepository;

    @GetMapping("/{user_id}")
    public ResponseEntity getUserInfo(@PathVariable(name = "user_id") String userId){
        User user = null;
        user = userRepository.findByUserId(userId);

        if()

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
