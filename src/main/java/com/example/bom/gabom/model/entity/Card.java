package com.example.bom.gabom.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Card{

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //"카드 id" + "인덱스"
    private Long cardId;

    @ManyToOne
    @JoinColumn(name = "pin_id")
    private Pin pin;

    //카드의 본문 내용
    @NotNull
    private String content;

    //본문의 이미지 객체를 찾아야함.
    @NotNull
    @Column(name = "image_path")
    private String imagePath;

}
