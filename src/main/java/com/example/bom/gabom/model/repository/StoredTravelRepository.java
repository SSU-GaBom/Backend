package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.entity.StoredTravel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredTravelRepository extends JpaRepository<StoredTravel, Long> {
}
