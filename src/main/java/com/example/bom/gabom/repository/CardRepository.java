package com.example.bom.gabom.repository;

import com.example.bom.gabom.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
