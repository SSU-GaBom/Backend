package com.example.bom.gabom.controller;

import com.example.bom.gabom.advice.exception.CEmailSigninFailedException;
import com.example.bom.gabom.advice.exception.CCheckIdFailedException;
import com.example.bom.gabom.configuration.security.JwtTokenProvider;
import com.example.bom.gabom.dto.UserDto;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import com.example.bom.gabom.model.response.CommonResult;
import com.example.bom.gabom.model.response.SingleResult;
import com.example.bom.gabom.service.ResponseService;
import com.example.bom.gabom.service.SignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/sign")
public class SignController {

    private final UserRepository userRepository; // jpa 쿼리 활용
    private final JwtTokenProvider jwtTokenProvider; // jwt 토큰 생성
    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화
    private final SignUpService signUpService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        User user = userRepository.findByUserId(id).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            // matches : 평문, 암호문 패스워드 비교 후 boolean 결과 return
            throw new CEmailSigninFailedException();
        }
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getUserId()), user.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@RequestBody UserDto userDto) {
        signUpService.joinUser(userDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "id중복체크")
    @PostMapping(value = "/checkid")
    public CommonResult checkId(@RequestParam HashMap<String, String> userId){
        if(!signUpService.checkId(userId.get("userId")))
            throw new CCheckIdFailedException();
        return responseService.getSuccessResult();
    }

    //CommonResult들은 json 안에 success로 boolean값이 있음. 아이디 중복체크, 이메일 체크 두개 모두 true로 넘겨 받았을 때 아이디 생성이 가능.
//    @ApiOperation(value = "email 중복체크 및 email 인증")
//    @PostMapping("/authemail")
//    public CommonResult authEmail(@RequestParam HashMap<String, String> randNum,
//                                    @RequestParam HashMap<String, String> email,
//                                    HttpSession session) {
//        if(signUpService.authMail(randNum.get("randnum"), email.get("email"), session)){
//            return new ResponseEntity(true, HttpStatus.OK);
//        }
//        return new ResponseEntity(true, HttpStatus.OK);
//    }
}