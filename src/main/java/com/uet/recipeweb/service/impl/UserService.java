package com.uet.recipeweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uet.recipeweb.dto.LoginInfoDTO;
import com.uet.recipeweb.entity.LoginInfoEntity;
import com.uet.recipeweb.repository.LoginInfoRepository;
import com.uet.recipeweb.repository.UserRepository;
import com.uet.recipeweb.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LoginInfoRepository loginInfoRepository;

	@Override
	public List<String> suggestUserFullName(String keyword) {
		keyword = keyword.replaceAll("\\s","*");
		keyword = "*" + keyword + "*";
		return userRepository.suggestUserFullName(keyword);
	}

	@Override
	public List<LoginInfoDTO> findAllByUserNameContaining(String keyword) {
		List<LoginInfoEntity> loginInfoEntities = loginInfoRepository.findAllByUserNameContaining(keyword);
		List<LoginInfoDTO> loginInfoDTOs = new ArrayList<>();
		for (LoginInfoEntity loginInfoEntity : loginInfoEntities) {
			loginInfoDTOs.add(modelMapper.map(loginInfoEntity, LoginInfoDTO.class));
		}
		return loginInfoDTOs;
	}

	@Override
	public LoginInfoDTO setUserStatus(Long userId, Short status) {
		LoginInfoEntity loginInfoEntity = loginInfoRepository.getById(userId);
		loginInfoEntity.setStatus(status);
		loginInfoRepository.save(loginInfoEntity);
		return modelMapper.map(loginInfoEntity, LoginInfoDTO.class);
	}
}
