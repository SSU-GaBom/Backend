package com.example.bom.gabom.model.repository;

import com.example.bom.gabom.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserId(String userId);
<<<<<<< HEAD
=======
    public User findByUserId(String userId);

>>>>>>> 5ca243f0756cb84473278ba4b554ea5d8a28a824
}
