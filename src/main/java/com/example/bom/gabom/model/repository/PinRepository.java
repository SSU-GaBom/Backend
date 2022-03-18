package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.dao.Pin;
import com.example.bom.gabom.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepository extends JpaRepository<Pin, Long> {
}
