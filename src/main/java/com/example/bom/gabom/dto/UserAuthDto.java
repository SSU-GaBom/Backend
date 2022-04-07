package com.example.bom.gabom.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@RequiredArgsConstructor
public class UserAuthDto {
    private String email;
    private String randNum;
    private String password;
}
