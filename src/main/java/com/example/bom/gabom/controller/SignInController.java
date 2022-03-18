package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.dao.Pin;
import com.example.bom.gabom.model.repository.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignInController {

    @Autowired
    private PinRepository userRepository;

    @PostMapping("/post")
    public void userPost(@RequestBody Pin user){
        System.out.println(user.toString());
        userRepository.save(user);
    }
}
