package com.example.bom.gabom.repository;

import com.example.bom.gabom.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {

    @Query(value = "SELECT FROM EmailAuth e WHERE e.email := email AND e.authToken := authToken AND e.expireDate := currentTime", nativeQuery = true)
    Optional<EmailAuth> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime);
}
