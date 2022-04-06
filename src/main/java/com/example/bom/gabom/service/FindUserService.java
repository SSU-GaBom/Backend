package com.example.bom.gabom.service;

import com.example.bom.gabom.dto.FindUserDto;
import com.example.bom.gabom.dto.UserAuthDto;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FindUserService {

    private final UserRepository userRepository;
    private final AuthMailService authMailService;

    //return true면 유저가 존재해서 정상적으로 메일이 보내진거 false면 유저가 없거나 메일이 보내지지 않은 것
    @Transactional
    public Boolean findInfo(FindUserDto findUserDto, Integer statusnum, HttpSession session){
        //여기는 난수 생성 후 비교까지는 하지 않은 단계임. 이후에 난수랑 비교해서 같으면 그 때 아이디를 넘겨줘야함.
        return authMailService.authMail(findUserDto, statusnum, session);
    }

    //난수를 비교한다. 비교하여 이메일로 유저를 찾아낸다.
    @Transactional
    public User comparison(String email, String randnum, HttpSession session){
        String sessrandnum = (String)session.getAttribute(email);

        if(sessrandnum.equals(randnum))
            return userRepository.findByEmail(email).orElseThrow();
        return null;
    }

    //넘겨 받은 객체로 이메일을 통해 유저 정보를 얻어서 비밀번호를 업데이트한다.
    @Transactional
    public boolean setPassword(UserAuthDto userAuthDto){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userRepository.updatePassWord(encoder.encode(userAuthDto.getPassword()), userRepository.findByEmail(userAuthDto.getEmail()).orElseThrow().getUserNo());

        if(encoder.matches(userAuthDto.getPassword(), userRepository.findByEmail(userAuthDto.getEmail()).orElseThrow().getUserPw()))
            return true;

        return false;
    }
}
