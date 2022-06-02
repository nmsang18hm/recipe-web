package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uet.recipeweb.entity.IngredientEntity;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {
	@Query(value = "SELECT DISTINCT i.id AS id, i.name AS name FROM ingredient AS i "
			+ "INNER JOIN recipe_has_ingredient as ri ON i.id = ri.ingredient_id "
			+ "INNER JOIN recipe as r ON ri.recipe_id = r.id "
			+ "WHERE MATCH(name) AGAINST (?1 IN BOOLEAN MODE) AND r.is_allowed = 1", nativeQuery = true)
	List<IngredientEntity> search(String keyword);
}