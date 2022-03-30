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

    //이메일과 이름을 넘기면 난수를 생성하고 정상적으로 생성 됐는지 true false로 결과 값 출력
    @PostMapping("/idexist")
    public Boolean isUserIdExist(@RequestBody FindUserDto finduserDto, HttpSession session) {
        return findUserService.findInfo(finduserDto, FIND_ID, session);
    }

    //이메일과 아이디를 넘기면 난수를 생성하고 정상적으로 생성 됐는지 true false로 결과 값 출력
    @PostMapping("/pwexist")
    public Boolean isUserPwExist(@RequestBody FindUserDto findUserDto, HttpSession session) {
        return findUserService.findInfo(findUserDto, FIND_PW, session);
    }

    //여기는 requestparam으로 post 받아야함.
    @PostMapping("/showid")
    public String showId(@RequestParam("email") String email,
                         @RequestParam("randomnum") String randomnum,
                         HttpSession session) {

        try {
            User user = findUserService.comparison(email, randomnum, session);
            if (user == null)
                return "값이 다릅니다..";
            else
                return user.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            return "에러여~";
        }
    }

    //비밀번호와 비밀번호 확인 부분은 막아두다가 여기서 true 되면 활성화
    @PostMapping("/authpw")
    public Boolean authPw(@RequestParam String email,
                          @RequestParam String randomnum,
                          HttpSession session) {
        try {
            User user = findUserService.comparison(email, randomnum, session);
            if (user == null)
                return false;
            else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/changepw")
    public String changePw(@RequestParam String email,
                           @RequestParam String randomnum,
                           @RequestParam String passwd,
                           @RequestParam String checkpasswd) {

        try {
            User user = findUserService.comparison(email, randomnum, session);
            if (user == null)
                return "난수의 값이 다릅니다.";
            else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }


}
