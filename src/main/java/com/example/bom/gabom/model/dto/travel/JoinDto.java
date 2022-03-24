package com.example.bom.gabom.model.dto.travel;


import com.example.bom.gabom.model.entity.Pin;
import com.example.bom.gabom.model.entity.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

public class JoinDto {
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    //리뷰 내부의 핀 리스트
    @OneToMany(mappedBy = "travel")
    private List<Pin> pinList;

    //리뷰 제목
    @NotNull
    private String title;

}
