package com.uet.recipeweb.service;

import java.util.List;

import com.uet.recipeweb.dto.LoginInfoDTO;

public interface IUserService {
	public List<String> suggestUserFullName(String keyword);
	public List<LoginInfoDTO> findAllByUserNameContaining(String keyword);
	public LoginInfoDTO setUserStatus(Long userId ,Short status);
}
