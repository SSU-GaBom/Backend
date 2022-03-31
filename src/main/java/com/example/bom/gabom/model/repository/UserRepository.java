package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserId(String userId);
    public boolean existsByEmail(String email);
    public User findByUserId(String userId);
    public User findByEmail(String email);

    @Modifying
    @Query("Update User u SET u.userPw = :password where u.userNo = :userNo")
    public void updatePassWord(String password, Long userNo);
}
