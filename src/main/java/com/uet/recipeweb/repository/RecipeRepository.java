package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uet.recipeweb.entity.RecipeEntity;;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
	@Query(value = "SELECT title FROM recipe WHERE MATCH(title) "
			+ "AGAINST (?1 IN BOOLEAN MODE) AND is_allowed = 1", nativeQuery = true)
	public List<String> suggestRecipeName(String keyword);
	
	@Query(value = "SELECT * FROM recipe WHERE MATCH(title) "
			+ "AGAINST (?1 IN BOOLEAN MODE) AND is_allowed = 1", nativeQuery = true)
	public List<RecipeEntity> search(String keyword);
	
	@Query(value = "SELECT r.id, title, short_description, main_image_url, owner_id, r.total_views - ifnull(rw.total_views, 0) AS total_views, is_allowed, amount_of_people, cooking_time, create_time, last_update FROM recipe AS r "
			+ "LEFT JOIN recipe_per_week AS rw ON r.id = rw.id "
			+ "WHERE r.is_allowed = 1 "
			+ "ORDER BY total_views DESC "
			+ "LIMIT 10", nativeQuery = true)
	public List<RecipeEntity> findTop10ByViewWeek();
	
	@Query(value = "SELECT r.id, title, short_description, main_image_url, owner_id, count(l.recipe_id) - ifnull(rw.total_likes, 0) AS total_views, is_allowed, amount_of_people, cooking_time, create_time, last_update FROM recipe AS r "
			+ "LEFT JOIN recipe_per_week AS rw ON r.id = rw.id "
			+ "LEFT JOIN `like` AS l ON r.id = l.recipe_id "
			+ "WHERE r.is_allowed = 1 "
			+ "GROUP BY r.id "
			+ "ORDER BY total_views DESC "
			+ "LIMIT 10", nativeQuery = true)
	public List<RecipeEntity> findTop10ByLikeWeek();
	
	@Query(value = "SELECT r.id, title, short_description, main_image_url, owner_id, r.total_views - ifnull(rm.total_views, 0) AS total_views, is_allowed, amount_of_people, cooking_time, create_time, last_update FROM recipe AS r "
			+ "LEFT JOIN recipe_per_month AS rm ON r.id = rm.id "
			+ "WHERE r.is_allowed = 1 "
			+ "ORDER BY total_views DESC "
			+ "LIMIT 10", nativeQuery = true)
	public List<RecipeEntity> findTop10ByViewMonth();
	
	@Query(value = "SELECT r.id, title, short_description, main_image_url, owner_id, count(l.recipe_id) - ifnull(rm.total_likes, 0) AS total_views, is_allowed, amount_of_people, cooking_time, create_time, last_update FROM recipe AS r "
			+ "LEFT JOIN recipe_per_month AS rm ON r.id = rm.id "
			+ "LEFT JOIN `like` AS l ON r.id = l.recipe_id "
			+ "WHERE r.is_allowed = 1 "
			+ "GROUP BY r.id "
			+ "ORDER BY total_views DESC "
			+ "LIMIT 10", nativeQuery = true)
	public List<RecipeEntity> findTop10ByLikeMonth();
	
	@Query(value = "SELECT * FROM recipe WHERE is_allowed = 1 ORDER BY create_time DESC LIMIT 10", nativeQuery = true)
	public List<RecipeEntity> findTop10New();
	
	public List<RecipeEntity> findAllByIsAllowed(Short isAllowed);
}
