package com.example.bom.gabom.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@RequiredArgsConstructor
public class UserAuthDto {
    private String email;
    private String password;
}
