package com.example.bom.gabom.model.dto;

import com.example.bom.gabom.model.entity.Travel;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDto {

    private Long userNo;
    private String userId;
    private String userPw;
    private String email;
    private String userName;
    private String userAuth;
    private String appendDate;
    private String updateDate;
    private String profileImagePath;
    private Integer following;
    private List<Travel> myTravelList;
    private List<Travel> likedTravelList;

    //내가 분류하여 저장한 리뷰 리스트(분류 자체로 리스트여야 하고 분류 후에도 리스트여야 해서 고민 해야함.)
    /*
    @OneToMany
    @JoinColumn(name = "stored_travel_id")
    private List<StoredTravel> storedTravelList;
     */

}
