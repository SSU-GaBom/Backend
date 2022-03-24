package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserId(String userId);

    User findByUserName(String username);
//
}
