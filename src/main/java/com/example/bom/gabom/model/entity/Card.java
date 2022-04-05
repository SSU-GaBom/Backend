package com.example.bom.gabom.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    @Column(name = "card_image")
    @OneToMany(mappedBy = "card")
    private List<Image> cardImage = new ArrayList<>();
}
