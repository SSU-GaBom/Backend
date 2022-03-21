package com.example.bom.gabom.model.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Travel{

    @Id
    @Column(name = "travel_id")
    //카드 아이디는 "UserID" + "인덱스 번호"로 하면 될 듯?
    private String travelId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //리뷰 내부의 핀 리스트
    @OneToMany(mappedBy = "travel")
    private List<Pin> pinList;

    //리뷰 제목
    private String title;

    //생성 시각
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    //수정 시각각
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt = LocalDateTime.now();

    //공유 여부
    private boolean isShared;
    //좋아요 갯수
    private Integer likedCount;
}
