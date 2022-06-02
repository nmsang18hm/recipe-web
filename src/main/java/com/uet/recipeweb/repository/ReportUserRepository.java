package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uet.recipeweb.entity.ReportUserEntity;

public interface ReportUserRepository extends JpaRepository<ReportUserEntity, Integer> {
	public List<ReportUserEntity> findAllByStatus(Short status);
}
