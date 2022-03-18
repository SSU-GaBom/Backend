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
public class Pin {

    @Id
    @Column(name = "pin_id")
    //Pin의 번호는 "트레블 ID" + "인덱스"
    private String pinId;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @OneToMany(mappedBy = "pin")
    private List<Card> cardList;

}
