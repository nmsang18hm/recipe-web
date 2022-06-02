package com.uet.recipeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uet.recipeweb.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
