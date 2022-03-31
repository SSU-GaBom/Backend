package com.example.bom.gabom.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@RequiredArgsConstructor
public class UserAuthDto {
    String email;
    String randNum;
    String password;
}
