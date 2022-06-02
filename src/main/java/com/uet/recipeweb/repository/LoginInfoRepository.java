package com.uet.recipeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uet.recipeweb.entity.LoginInfoEntity;

public interface LoginInfoRepository extends JpaRepository<LoginInfoEntity, Long> {
	public LoginInfoEntity findOneByUserName(String userName);
	public List<LoginInfoEntity> findAllByUserNameContaining(String keyword);
}
