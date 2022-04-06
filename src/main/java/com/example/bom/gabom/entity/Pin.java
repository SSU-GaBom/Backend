package com.example.bom.gabom.entity;

import com.example.bom.gabom.model.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pin{

    @Id
    @Column(name = "pin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Pin의 번호는 "트레블 ID" + "인덱스"
    private Long pinId;

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
    private List<Card> cardList=new ArrayList<>();

    public void add(Card card){
        card.setPin(this);
        this.cardList.add(card);
    }

}
