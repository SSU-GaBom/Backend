package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.dao.Card;
import com.example.bom.gabom.model.dao.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
