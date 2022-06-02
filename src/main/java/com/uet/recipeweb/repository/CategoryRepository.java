package com.uet.recipeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uet.recipeweb.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
