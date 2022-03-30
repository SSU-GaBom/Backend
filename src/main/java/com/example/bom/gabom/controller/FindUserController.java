package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.dto.FindUserDto;
import com.example.bom.gabom.model.dto.UserAuthDto;
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

    //이메일과 이름을 넘기면 난수를 생성하고 이메일칸, 이름 칸은 비활성한다. 그리고 인증번호를 치는 창이 활성화 된다. 정상적으로 생성 됐는지 true false로 결과 값 출력
    @PostMapping("/idexist")
    public Boolean isUserIdExist(@RequestBody FindUserDto finduserDto, HttpSession session) {
        return findUserService.findInfo(finduserDto, FIND_ID, session);
    }

    //이메일과 아이디를 넘기면 난수를 생성하고 이메일칸, 아이디 칸은 비활성한다. 그리고 인증번호 치는 창이 활성화 된다. 정상적으로 생성 됐는지 true false로 결과 값 출력
    @PostMapping("/pwexist")
    public Boolean isUserPwExist(@RequestBody FindUserDto findUserDto, HttpSession session) {
        return findUserService.findInfo(findUserDto, FIND_PW, session);
    }

    //여기는 requestparam으로 post 받아야함.
    @PostMapping("/showid")
    public String showId(@RequestBody UserAuthDto userAuthDto,
                         HttpSession session) {

        try {
            User user = findUserService.comparison(userAuthDto.getEmail(), userAuthDto.getRandomnum(), session);
            if (user == null)
                return "값이 다릅니다..";
            else
                return user.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            return "에러여~";
        }
    }

    @PostMapping("/changepw")
    public String changePw(@RequestBody ) {

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

    //인증이 되면 이메일, 아이디가 비활성화 이후
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
}
