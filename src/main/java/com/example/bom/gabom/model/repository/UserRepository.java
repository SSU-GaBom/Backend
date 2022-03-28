package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserId(String userid);
    public User findByUserId(String userid);
    public User findByEmailAndUserName(String email, String name);
    public User findByEmailAndUserNameAndUserId(String email, String name, String userid);
}
