package com.example.bom.gabom.controller;

import com.example.bom.gabom.advice.exception.CUserIdAlreadyExistsException;
import com.example.bom.gabom.advice.exception.CNickNameAlreadyExistsException;
import com.example.bom.gabom.dto.LoginDto;
import com.example.bom.gabom.dto.UserDto;
import com.example.bom.gabom.model.response.CommonResult;
import com.example.bom.gabom.model.response.SingleResult;
import com.example.bom.gabom.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/sign")
public class SignController {

    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final SignUpService signUpService;
    private final ConfirmationTokenService confirmationTokenService;
    private final LogInService logInService;
    private final CheckService checkService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@RequestBody LoginDto loginDto) {
        return logInService.signIn(loginDto);
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@RequestBody UserDto userDto) throws MessagingException {
        //아이디, 닉네임, 이메일 중복 확인
        checkService.check(userDto);
        confirmationTokenService.createEmailConfirmationToken(userDto.getUserId(), userDto.getEmail());
        signUpService.joinUser(userDto);
        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "id중복체크")
    @GetMapping(value = "/check-id")
    public CommonResult checkId(@RequestBody UserDto userDto){
        if(!checkService.checkId(userDto.getUserId()))
            throw new CUserIdAlreadyExistsException();
        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "닉네임 중복체크")
    @GetMapping(value = "/check-nickname")
    public CommonResult checkNickName(@RequestBody UserDto userDto){
        if(!checkService.checkNickName(userDto.getNickName()))
            throw new CNickNameAlreadyExistsException();
        return responseService.getSuccessResult();
    }
}