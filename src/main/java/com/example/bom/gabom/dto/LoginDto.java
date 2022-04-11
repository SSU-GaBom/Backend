package com.example.bom.gabom.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class LoginDto {
    private String loginId;
    private String loginPw;
}
