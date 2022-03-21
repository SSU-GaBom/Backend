package com.example.bom.gabom.model.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User{

    @Id
    @Column(name = "user_id")
    private String id;
    private String password;
    private String checkPassword;
    private String email;
    private String name;

    //내가 쓴 리뷰 리스트
    @OneToMany(mappedBy = "user")
    private List<Travel> myTravelList;

    //찜을 누른 리뷰 리스트
    private List<Travel> likedTravelList;

    //내가 분류하여 저장한 리뷰 리스트(분류 자체로 리스트여야 하고 분류 후에도 리스트여야 해서 고민 해야함.)
    private
}
