package com.example.bom.gabom.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import  javax.persistence.Id;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Repository
public class Location {
    @Id
    private String id;
}
