package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uet.recipeweb.entity.ReportCommentEntity;

public interface ReportCommentRepository extends JpaRepository<ReportCommentEntity, Integer> {
	public List<ReportCommentEntity> findAllByStatus(Short status);
}
