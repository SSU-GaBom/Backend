package com.example.bom.gabom.entity;

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
public class Location{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;


    //도로명 주소 or 지번 주소
    @NotNull
    private String address;

    //가게 이름이 있는 경우 넣음. (null 가능)
    private String name;

    //위도
    @NotNull
    private Float latitude;

    //경도
    @NotNull
    private Float longitude;

    //이 두개는 보류
    private Integer city;
    private Integer state;
}
