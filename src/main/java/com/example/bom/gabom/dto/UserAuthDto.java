package com.example.bom.gabom.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAuthDto {
    String email;
    String randNum;
    String password;
}
