package com.example.bom.gabom.model.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Location{

    //도로명 주소 or 지번 주소
    @Id
    private String address;

    //가게 이름이 있는 경우 넣음. (null 가능)
    private String id;

    //위도
    private Integer latitude;

    //경도
    private Integer longitude;

    //지역 하나를 여러 사람들이 올렸을 수 있음.
    @OneToMany
    private List<Pin> pinList;
}
