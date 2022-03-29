package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.dto.UserDto;
import com.example.bom.gabom.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    boolean isChecked = false;

    private final SignUpService signUpService;


    //checkId로 post할 때 json으로 넘어오므로 hashmap으로 (key:userid, value:값)으로 파싱을 해줘야함.
    @PostMapping("/checkid")
    public Boolean checkId(@RequestParam String userId) {
        //false일 경우 계정이 있는 것 -> 계정 생성 불가
        return signUpService.checkId(userId);
    }

    //이메일이 존재하는지 체크하는 버튼을 먼저 클릭해서 확인한 후 인증 이메일을 통해서 authemail메소드를 통해서 이메일 인증을 진행.
    @PostMapping("/checkemail")
    public Boolean checkEmail(@RequestParam String email) {
        //false일 경우 이메일이 있는 것 -> 계정 생성 불가
        return signUpService.checkEmail(email);
    }

    //이건 이메일 인증을 위해서 이메일을 보내는 컨트롤러
    @PostMapping("/sendemail")
    public Boolean sendEmail(@RequestParam String email, HttpSession session) {
        return signUpService.sendEmail(email, session);
    }

    //이건 이메일로 보내진 난수를 체크하는 컨트롤러
    @PostMapping("/authemail")
    public Boolean authEmail(@RequestParam String randnum,
                             @RequestParam String email,
                             HttpSession session) {
        return signUpService.authMail(randnum, email, session);
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserDto userDto) {
        if (isChecked = true)
            try {
                //계정 생성 서비스 실행
                signUpService.joinUser(userDto);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
            }
        else
            return new ResponseEntity<>("do check id", HttpStatus.OK);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
