package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uet.recipeweb.entity.CollectionEntity;;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
	@Query(value = "SELECT name FROM collection WHERE MATCH(name) "
			+ "AGAINST (?1 IN BOOLEAN MODE) AND is_public = 1", nativeQuery = true)
	public List<String> suggestCollectionName(String keyword);
	
	@Query(value = "SELECT * FROM collection WHERE MATCH(name) "
			+ "AGAINST (?1 IN BOOLEAN MODE) AND is_public = 1", nativeQuery = true)
	public List<CollectionEntity> search(String keyword);
	
	@Query(value = "SELECT c.*, count(uc.collection_id) - IFNULL(cm.total_likes, 0) AS total_likes FROM collection AS c "
			+ "LEFT JOIN collection_per_month AS cm ON c.id = cm.id "
			+ "LEFT JOIN user_save_collection AS uc ON c.id = uc.collection_id "
			+ "WHERE c.is_public = 1 "
			+ "GROUP BY c.id "
			+ "ORDER BY total_likes DESC "
			+ "LIMIT 10", nativeQuery = true)
	public List<CollectionEntity> findTop10ByLikeMonth();
	
	@Query(value = "SELECT * FROM collection WHERE is_public = 1 ORDER BY create_time DESC LIMIT 10", nativeQuery = true)
	public List<CollectionEntity> findTop10New();
}
