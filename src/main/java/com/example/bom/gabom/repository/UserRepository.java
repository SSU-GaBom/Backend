package com.example.bom.gabom.repository;

import com.example.bom.gabom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserId(String userId);
    public boolean existsByUserNameAndEmail(String userName, String email);
    public boolean existsByUserIdAndEmail(String userId, String email);
    public boolean existsByNickName(String NickName);
    //public User findByUserId(String userId);
    public Optional<User> findByUserId(String userId);
    public Optional<User> findByUserNameAndEmail(String userName, String email);
    public Optional<User> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("Update User u SET u.userPw = :password where u.userNo = :userNo")
    public void updatePassWord(String password, Long userNo);
}
