package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.dto.FindUserDto;
import com.example.bom.gabom.model.dto.UserDto;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/finduser")
@RequiredArgsConstructor
public class FindUserController {

    private final Integer FIND_ID = 1;
    private final Integer FIND_PW = 2;

    private final FindUserService findUserService;

    //이메일 난수 인증 페이지 새로 만들어야 함.
    @PostMapping("/findid")
    public Boolean findId(@RequestBody FindUserDto finduserDto, HttpSession session){
        return findUserService.findInfo(finduserDto, FIND_ID, session);
    }

    //public ResponseEntity emailAuth

    @PostMapping("/findpw")
    public Boolean findPw(@RequestBody FindUserDto findUserDto, HttpSession session){
        return findUserService.findInfo(findUserDto, FIND_PW, session);
    }

    @PostMapping("/comparisonid")
    public String comparisonId(@RequestParam String email,
                             @RequestParam String randomnum,
                             HttpSession session){
        try {
            return findUserService.comparison(email, randomnum, session).getUserId();
        }catch(Exception e){
            return "에러여~";
        }
    }

    @PostMapping("/comparisonpw")
    public ResponseEntity comparisonPw(@RequestParam String email,
                                       @RequestParam String randomnum,
                                       HttpSession session){
        findUserService.comparison(email, randomnum, session);
        return

    }
}
