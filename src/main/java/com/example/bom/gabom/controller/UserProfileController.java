package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.response.SingleResult;
import com.example.bom.gabom.service.ResponseService;
import com.example.bom.gabom.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final ResponseService responseService;

    @GetMapping("/{user_id}")
    public SingleResult getUserInfo(@PathVariable(name = "user_id") String userId){
        userProfileService.showInfo(userId);
        return responseService.getSingleResult(userProfileService);
    }
}
