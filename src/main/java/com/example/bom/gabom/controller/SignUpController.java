package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("")
    public ResponseEntity register(@RequestBody User user){
        try {
            //계정 생성 서비스 실행
            signUpService.joinUser(user);
        }catch(Exception e){
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/validation")
    public String func(){
        return "validation";
    }

    //checkId로 post할 때 json으로 넘어오므로 hashmap으로 (key:userid, value:값)으로 파싱을 해줘야함.
    @PostMapping("/checkid")
    public HashMap<String, Boolean> checkId(@RequestBody HashMap<String, String> reqUserIdJson){
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            String reqUserId = reqUserIdJson.get("userId");

            //true일 경우 계정이 있는 것 -> 계정 생성 불가
            map.put("isExist", signUpService.checkId(reqUserId));
        }catch(Exception e) {
            map.put("isExist", false);
            return map;
        }
        return map;
    }


}
