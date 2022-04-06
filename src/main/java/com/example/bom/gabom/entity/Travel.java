package com.example.bom.gabom.entity;

import com.example.bom.gabom.dto.UpdateTravelDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Travel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long travelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @JsonIgnore //순환참조 방지용인데 다른방식으로 해야하나?
    private User user;

//    리뷰 내부의 핀 리스트
    @OneToMany(mappedBy = "travel")
    @Column(name = "pin_list")
    private List<Pin> pinList=new ArrayList<>();

    //리뷰 제목
    @NotNull
    private String title;

    //생성 시각
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    //수정 시각각
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt = LocalDateTime.now();

    //여행 시작 날짜
    @Column(name = "start_date")
    private LocalDateTime startDate;

    //여행 종료 날짜
    @Column(name = "end_date")
    private LocalDateTime endDate;

//    공유 여부
    @NotNull
    private Boolean isShared;
    //좋아요 갯수
    @NotNull
    private Integer likedCount;

    //도 or 특별시
    @NotNull
    private String state;

    //시 or 군
    @NotNull
    private String city;

    //여행 경비, 본문, 교통수단
    private Integer expense;
    private String content;
    private Transportation transportation;



    //user에 안들어가지는것같음.

    //    연관 관계 편의 메소드
    public void add(Pin pin){
        pin.setTravel(this);
        this.pinList.add(pin);
    }


    public Travel(User user, String title, Boolean isShared, Integer likedCount, String state, String city) {
        this.user = user;
        this.title = title;
        this.isShared = isShared;
        this.likedCount = likedCount;
        this.state = state;
        this.city = city;
    }
    public Travel(String title, Boolean isShared, Integer likedCount, String state, String city) {
        this.title = title;
        this.isShared = isShared;
        this.likedCount = likedCount;
        this.state = state;
        this.city = city;
    }

    public static Travel CreateTravel(User user, String title, Boolean isShared, Integer likedCount, String state, String city) {
        Travel travel = new Travel();
        travel.setUser(user);
        travel.title = title;
        travel.isShared = isShared;
        travel.likedCount = likedCount;
        travel.state = state;
        travel.city = city;
        return travel;
    }
    //업데이트
    public void updateTravel(UpdateTravelDto updateTravelDto){
        this.title = updateTravelDto.getTitle();
        this.content = updateTravelDto.getContent();
    }
}
