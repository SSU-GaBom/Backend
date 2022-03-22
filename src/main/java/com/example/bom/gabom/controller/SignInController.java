package com.example.bom.gabom.controller;

import com.example.bom.gabom.SessionConstraints;
import com.example.bom.gabom.model.dto.User;
import com.example.bom.gabom.model.vo.LoginDto;
import com.example.bom.gabom.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class SignInController {
    @Autowired
    SignInService signInService;

    //checkId로 post할 때 json으로 넘어오므로 hashmap으로 (key:userid, value:값)으로 파싱을 해줘야함.
    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest, BindingResult bindingResult){
        User user = null;

        if(bindingResult.hasErrors())
            return new ResponseEntity<>("오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

        String loginid = loginDto.getLoginId();
        String passwd = loginDto.getLoginPw();

        try {
            user = signInService.signIn(loginid, passwd);

            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute(SessionConstraints.Login_User, user);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("로그인 성공했습니다.", HttpStatus.OK);
    }
}
