package com.example.bom.gabom.model.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Card{

    @Id
    @Column(name = "card_id")
    //"카드 id" + "인덱스"
    private String cardId;

    @ManyToOne
    @JoinColumn(name = "pin_id")
    private Pin pin;

    //카드의 본문 내용
    private String body;

    //본문의 이미지 객체를 찾아야함.
    //private

}
