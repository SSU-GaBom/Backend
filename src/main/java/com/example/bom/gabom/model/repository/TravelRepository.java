package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
