package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uet.recipeweb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	@Query(value = "SELECT full_name FROM user WHERE MATCH(full_name) "
			+ "AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
	public List<String> suggestUserFullName(String keyword);
}
