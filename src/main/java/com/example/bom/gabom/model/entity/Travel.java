package com.example.bom.gabom.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Travel{

    @Id
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    //카드 아이디는 "UserID" + "인덱스 번호"로 하면 될 듯?
    private Long travelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    //리뷰 내부의 핀 리스트
    @OneToMany(mappedBy = "travel")
    private List<Pin> pinList;

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

    //공유 여부
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
    private Enum transportation;



    //user에 안들어가지는것같음.




//    public Travel(User user, String title, Boolean isShared, Integer likedCount, String state, String city) {
//        this.user = user;
//        this.title = title;
//        this.isShared = isShared;
//        this.likedCount = likedCount;
//        this.state = state;
//        this.city = city;
//    }
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
}
