package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uet.recipeweb.entity.ReportRecipeEntity;

public interface ReportRecipeRepository extends JpaRepository<ReportRecipeEntity, Integer> {
	public List<ReportRecipeEntity> findAllByStatus(Short status);
}
