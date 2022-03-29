package com.example.bom.gabom.service;

import com.example.bom.gabom.model.dto.FindUserDto;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    //
    @Transactional
    public User comparison(String email, String randomnum, HttpSession session){
        Object sessrandnum = session.getAttribute(email);

        if(sessrandnum.equals(randomnum))
            return userRepository.findByEmail(email);
        return
    }
}
