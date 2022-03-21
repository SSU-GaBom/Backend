package com.example.bom.gabom.model.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User{

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;

    //유저가 사용할 아이디
    @NotNull
    private String nickName;
    //비밀번호
    @NotNull
    private String password;
    //이메일
    @NotNull
    @Email
    private String email;
    //유저 실명
    @NotNull
    private String realName;

    //유저 프로필이 저장될 경로
    @NotNull
    @Column(name = "profile_image_path")
    private String profileImagePath;
    //유저를 팔로우한 사람 수
    @NotNull
    private Integer following;

    //내가 쓴 리뷰 리스트
    @OneToMany(mappedBy = "user")
    @Column(name = "my_travel_list")
    private List<Travel> myTravelList;

    //찜을 누른 리뷰 리스트
    @OneToMany
    @JoinColumn(name = "travel_id")
    @Column(name = "liked_travel_list")
    private List<Travel> likedTravelList;

    //내가 분류하여 저장한 리뷰 리스트(분류 자체로 리스트여야 하고 분류 후에도 리스트여야 해서 고민 해야함.)
    /*
    @OneToMany
    @JoinColumn(name = "stored_travel_id")
    private List<StoredTravel> storedTravelList;
     */
}
