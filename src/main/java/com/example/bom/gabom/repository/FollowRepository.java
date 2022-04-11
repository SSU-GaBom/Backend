package com.example.bom.gabom.repository;

import com.example.bom.gabom.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {



    @Query("SELECT count(f.fromUser) FROM Follow f WHERE f.toUser = :userId")
    public int findFollowerCountByUserId(String userId);

    @Query("SELECT COUNT(f.toUser) FROM Follow f WHERE f.fromUser = :userId")
    public int findFollowingCountByUserId(String userId);
}
