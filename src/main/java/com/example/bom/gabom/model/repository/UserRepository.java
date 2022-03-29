package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserId(String userid);
    public boolean existsByEmail(String Email);
    public User findByUserId(String userid);
    public User findByEmail(String email);
}
