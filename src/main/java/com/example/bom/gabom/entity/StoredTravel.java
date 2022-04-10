package com.example.bom.gabom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StoredTravel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stored_travel_id")
    private Long storedTravelId;
//
//    //저장할 여행 리스트
//    @OneToMany
//    @JoinColumn(name = "travel_id")
//    private List<Travel> travelList;
}
