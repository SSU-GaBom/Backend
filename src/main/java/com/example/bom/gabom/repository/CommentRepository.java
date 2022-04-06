package com.example.bom.gabom.repository;

import com.example.bom.gabom.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
