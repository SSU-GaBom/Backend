package com.example.bom.gabom.model.entity;

import com.example.bom.gabom.model.dto.UserDto;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = @Index(name="email", columnList="user_id, email", unique = true))
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User{

    //notnull로 하면 자동 생성이 안되는 중임.
    @ApiModelProperty(value = "유저 number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    //유저가 사용할 아이디
    //@NotNull
    @Column(name = "user_id")
    private String userId;
    //비밀번호
    //@NotNull
    private String userPw;
    //이메일
    //@NotNull
    @Email
    private String email;
    //유저 실명
    //@NotNull
    private String userName;

    //userAuth??
    //@NotNull
    private String userAuth;

    //계정 추가 시각
    //@NotNull
    private String appendDate;

    //계정 수정 시각
    //@NotNull
    private String updateDate;

    //유저 프로필이 저장될 경로
    //@NotNull
    @Column(name = "profile_image")
    private Image profileImage;
    //유저를 팔로우한 사람 수
    //@NotNull
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

    @OneToMany
    @JoinColumn(name = "stored_travel_id")
    private List<StoredTravel> storedTravelList;


    public void setUserField(UserDto userDto){
        this.userId = userDto.getUserId();
        this.userPw = userDto.getUserPw();
        this.email = userDto.getEmail();
        this.userName = userDto.getUserName();
        this.userAuth = userDto.getUserAuth();
        this.appendDate = userDto.getAppendDate();
        this.updateDate = userDto.getUpdateDate();
        this.profileImagePath = userDto.getProfileImagePath();
        this.following = userDto.getFollowing();
        this.myTravelList = userDto.getMyTravelList();
        this.likedTravelList = userDto.getLikedTravelList();
    }
}
