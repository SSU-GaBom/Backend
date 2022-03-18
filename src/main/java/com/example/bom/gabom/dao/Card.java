package com.example.bom.gabom.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import  javax.persistence.Id;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
public class Card {

    @Id
    @Column(name = "card_id")
    //"카드 id" + "인덱스"
    private String cardId;

    @ManyToOne
    @JoinColumn(name = "pin_id")
    private Pin pin;

    private String body;
    //private

}
