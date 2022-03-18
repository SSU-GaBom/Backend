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
public class User {

    @Id
    @Column(name = "user_id")
    private String id;
    private String password;
    private String checkPassword;
    private String email;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Travel> travelList;
}
