package com.example.bom.gabom.controller;

import com.example.bom.gabom.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SignUpService signUpService;

    @PostMapping("/login")
    public String func1(){
        return "login";
    };

    @GetMapping("/logout")
    public String func2(){
        return "logout";
    }

    @GetMapping("/findId") //아이디찾기
    public String func3(){
        return "login";
    };






}
