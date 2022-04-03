package com.example.bom.gabom.repository;

import com.example.bom.gabom.model.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Travel findByTravelId(Long travelId);

    @Transactional
    void deleteByTravelId(Long travelId);
}
