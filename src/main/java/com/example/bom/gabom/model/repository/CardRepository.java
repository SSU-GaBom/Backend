package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.dto.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
