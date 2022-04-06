package com.example.bom.gabom.controller;

import com.example.bom.gabom.dto.UserDto;
import com.example.bom.gabom.service.SignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/signup")
@Api(tags = "회원가입 컨트롤러입니다.")
@RequiredArgsConstructor
public class SignUpController {

    boolean isChecked = false;

    private final SignUpService signUpService;


    //checkId로 post할 때 json으로 넘어오므로 hashmap으로 (key:userid, value:값)으로 파싱을 해줘야함.
    //checkparam인 경우에는 직접 key:value로 해줘야 json 파싱이 가능함.
    @ApiOperation(value = "아이디의 중복 여부를 확인하는 메소드")
    @PostMapping("/checkid")
    public ResponseEntity checkId(@RequestParam HashMap<String, String> userId) {
        //false일 경우 계정이 있는 것 -> 계정 생성 불가
        if(signUpService.checkId(userId.get("userId"))){
            isChecked = true;
            return new ResponseEntity(true, HttpStatus.OK);
        }
        isChecked = false;
        return new ResponseEntity(false, HttpStatus.OK);
    }

    //이메일이 존재하는지 체크하는 버튼을 먼저 클릭해서 확인한 후 인증 이메일을 통해서 authemail메소드를 통해서 이메일 인증을 진행.
    @PostMapping("/checkemail")
    public ResponseEntity checkEmail(@RequestParam HashMap<String, String> email, HttpSession session) {
        if(signUpService.checkEmail(email.get("email")))
            return new ResponseEntity("이메일이 존재합니다.", HttpStatus.OK);
        //false일 경우 이메일이 있는 것 -> 계정 생성 불가
        return new ResponseEntity(signUpService.sendEmail(email.get("email"), session), HttpStatus.OK);
    }

    //이건 이메일로 보내진 난수를 체크하는 컨트롤러
    @PostMapping("/authemail")
    public ResponseEntity authEmail(@RequestParam HashMap<String, String> randNum,
                                    @RequestParam HashMap<String, String> email,
                                    HttpSession session) {
        if(signUpService.authMail(randNum.get("randnum"), email.get("email"), session)){
            isChecked = true;
            return new ResponseEntity(true, HttpStatus.OK);
        }
        isChecked = false;
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserDto userDto) {
        if (isChecked = true)
            try {
                //계정 생성 서비스 실행
                signUpService.joinUser(userDto);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        else
            return new ResponseEntity<>(false, HttpStatus.OK);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
