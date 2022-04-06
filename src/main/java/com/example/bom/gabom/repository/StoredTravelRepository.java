package com.example.bom.gabom.repository;

import com.example.bom.gabom.entity.StoredTravel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredTravelRepository extends JpaRepository<StoredTravel, Long> {
}
