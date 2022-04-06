package com.example.bom.gabom.repository;

import com.example.bom.gabom.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepository extends JpaRepository<Pin, Long> {
}
