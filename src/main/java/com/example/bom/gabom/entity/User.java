package com.example.bom.gabom.entity;

import com.example.bom.gabom.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User implements UserDetails {

    //notnull로 하면 자동 생성이 안되는 중임.
    @ApiModelProperty(value = "유저 number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    //유저가 사용할 아이디
    //@NotNull
    @Column(name = "user_id", unique = true)
    private String userId;
    //비밀번호
    //@NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPw;
    //이메일
    //@NotNull
    @Email
    @Column(unique = true)
    private String email;
    //유저 실명
    //@NotNull
    private String userName;

    private String nickName;

    //userAuth??
    //@NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>(); // 회원이 가지고 있는 권한 정보들

    //계정 추가 시각
    //@NotNull
    private String appendDate;

    //계정 수정 시각
    //@NotNull
    private String updateDate;

    //유저 프로필이 저장될 경로
    //@NotNull
//    @OneToOne(mappedBy = "user")
//    private Image profileImage;

    //유저를 팔로우한 사람 수
    //@NotNull
    private Integer following;

    private String provider;
    private String refreshToken;
    private Boolean emailAuth;

    //내가 쓴 리뷰 리스트
    @OneToMany(mappedBy = "user")
    @Column(name = "my_travel_list")
    private List<Travel> myTravelList = new ArrayList<>();

    //찜을 누른 리뷰 리스트
    @OneToMany
    @JoinColumn(name = "travel_id")
    @Column(name = "liked_travel_list")
    private List<Travel> likedTravelList = new ArrayList<>();

    //내가 분류하여 저장한 리뷰 리스트(분류 자체로 리스트여야 하고 분류 후에도 리스트여야 해서 고민 해야함.)

    @OneToMany
    @JoinColumn(name = "stored_travel_id")
    private List<StoredTravel> storedTravelList = new ArrayList<>();

    //이건 유저 이름 리턴하는건데 getUsername 오버라이드 된 것 때문에 오류 생겨서 getter로 안되는 듯
    public String getUserName(){
        return this.userName;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void emailVerifiedSuccess() {
        this.emailAuth = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    //이건 유일키 넘겨주는것
    @Override
    public String getUsername() {
        return this.userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
