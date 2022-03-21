package com.example.bom.gabom.model.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Location{

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "location_id")
    private Integer locationId;

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
