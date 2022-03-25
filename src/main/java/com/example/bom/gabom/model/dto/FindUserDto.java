package com.example.bom.gabom.model.vo;

import lombok.Data;

@Data
public class FindUserVo {

    //id 찾을 때만 쓰는 요소들
    private String email;
    private String name;

    //pw 찾을 때 쓰는 요소
    private String userId;
}
