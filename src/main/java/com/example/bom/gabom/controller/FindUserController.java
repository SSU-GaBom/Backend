package com.example.bom.gabom.controller;

import com.example.bom.gabom.dto.FindUserDto;
import com.example.bom.gabom.dto.UserAuthDto;
import com.example.bom.gabom.entity.User;
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
    //boolean type return임.
    @PostMapping("/idexist")
    public ResponseEntity isUserIdExist(@RequestBody FindUserDto finduserDto, HttpSession session) {
        return ResponseEntity.status(HttpStatus.OK).body(findUserService.findInfo(finduserDto, FIND_ID, session));
    }

    //이메일과 아이디를 넘기면 난수를 생성하고 이메일칸, 아이디 칸은 비활성한다. 그리고 인증번호 치는 창이 활성화 된다. 정상적으로 생성 됐는지 true false로 결과 값 출력
    //boolean type return 임.
    @PostMapping("/pwexist")
    public ResponseEntity isUserPwExist(@RequestBody FindUserDto findUserDto, HttpSession session) {
        return ResponseEntity.status(HttpStatus.OK).body(findUserService.findInfo(findUserDto, FIND_PW, session));
    }

    //여기는 requestparam으로 post 받아야함.
    //String type return
    @PostMapping("/showid")
    public ResponseEntity showId(@RequestBody UserAuthDto userAuthDto,
                         HttpSession session) {

        try {
            User user = findUserService.comparison(userAuthDto.getEmail(), userAuthDto.getRandNum(), session);
            if (user == null)
                return new ResponseEntity("값이 다릅니다..", HttpStatus.OK);
            else
                return new ResponseEntity(user.getUserId(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("에러여~", HttpStatus.OK);
        }
    }

    //비밀번호와 비밀번호 확인 부분은 막아두다가 여기서 true 되면 활성화
    @PostMapping("/authpw")
    public ResponseEntity authPw(@RequestBody UserAuthDto userAuthDto,
                          HttpSession session) {
        try {
            User user = findUserService.comparison(userAuthDto.getEmail(), userAuthDto.getRandNum(), session);
            if (user == null)
                return new ResponseEntity(false, HttpStatus.OK);
            else {
                return new ResponseEntity(true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }

    //프론트에서 확인 비밀번호와 그냥 비밀번호가 같다면 이메일과 함께
    @PostMapping("/changepw")
    public ResponseEntity changePw(@RequestBody UserAuthDto userAuthDto) {
        return new ResponseEntity(findUserService.setPassword(userAuthDto), HttpStatus.OK);
    }
}
