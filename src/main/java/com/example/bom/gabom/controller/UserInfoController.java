package com.example.bom.gabom.controller;

import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import com.example.bom.gabom.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/{user_id}")
    public ResponseEntity getUserInfo(@PathVariable(name = "user_id") String userId){
        User user = userInfoService.showInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
