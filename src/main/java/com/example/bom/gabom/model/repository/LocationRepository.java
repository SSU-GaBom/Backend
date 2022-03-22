package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.dto.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
