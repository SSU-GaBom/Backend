package com.example.bom.gabom.model.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pin{

    @Id
    @Column(name = "pin_id")
    //Pin의 번호는 "트레블 ID" + "인덱스"
    private Integer pinId;

    //단방향 다대1
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    //양방향 다대1
    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    //양방향 1대다
    @OneToMany(mappedBy = "pin")
    private List<Card> cardList;

}
