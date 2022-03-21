package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.dao.Pin;
import com.example.bom.gabom.model.dao.StoredTravel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredTravelRepository extends JpaRepository<StoredTravel, Long> {
}
