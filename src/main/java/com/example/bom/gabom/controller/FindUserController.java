package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.vo.FindUserVo;
import com.example.bom.gabom.service.FindIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/findId")
public class FindIdController {

    @Autowired
    FindIdService findIdService;

    public ResponseEntity (@RequestBody FindUserVo findUserVo, HttpServletRequest request, HttpSession session){

        String name = findUserVo.getName();
        String email = findUserVo.getEmail();


        
    }
}
