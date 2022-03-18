package com.example.bom.gabom.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import  javax.persistence.Id;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
public class Travel {

    @Id
    @Column(name = "travel_id")
    //카드 아이디는 "UserID" + "인덱스 번호"로 하면 될 듯?
    private String travelId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "travel")
    private List<Pin> pinList;

    private String title;
    private boolean isShared;

}
