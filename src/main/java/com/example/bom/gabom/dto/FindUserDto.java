package com.example.bom.gabom.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FindUserDto {

    //id 찾을 때만 쓰는 요소들
    private String email;
    private String userName;

    //pw 찾을 때 쓰는 요소
    private String userId;
}
