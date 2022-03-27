package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.dto.FindUserDto;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/finduser")
@RequiredArgsConstructor
public class FindUserController {

    private final FindUserService findUserService;

    //이메일 난수 인증 페이지 새로 만들어야 함.
    @PostMapping("/findid")
    public ResponseEntity findId(@RequestBody FindUserDto findUserDto, HttpSession session){

        String name = findUserDto.getUserName();
        String email = findUserDto.getEmail();
        User user;

        try {
            user = findUserService.findId(email, name, session);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity("userAuthSet", HttpStatus.OK);
    }

    //public ResponseEntity emailAuth

    @PostMapping("/findpw")
    public ResponseEntity findPw(@RequestBody FindUserDto findUserDto, HttpSession seesion){

        String name = findUserDto.getUserName();
        String email = findUserDto.getEmail();
        String userId = findUserDto.getUserId();


        return new ResponseEntity("userAuthSet", HttpStatus.OK);
    }
}
