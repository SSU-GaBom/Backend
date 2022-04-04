package com.example.bom.gabom.controller;

import com.example.bom.gabom.etc.SessionConstraints;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.model.dto.LoginDto;
import com.example.bom.gabom.service.LogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LogInController {

    LogInService logInService;

    //checkId로 post할 때 json으로 넘어오므로 hashmap으로 (key:userid, value:값)으로 파싱을 해줘야함.
    //User or String return
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto, HttpSession session, BindingResult bindingResult){
        User user = null;

        if(bindingResult.hasErrors())
            return new ResponseEntity<>("오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            //디비에서 검색하여 존재하면 user객체에 주입
            user = logInService.signIn(loginDto, session);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //주입 상태에 따라 출력값 다르게(로그인할 아이디가 없는 경우
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpSession session1){
        System.out.println(session1.getAttribute(SessionConstraints.Login_User));

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }
}
