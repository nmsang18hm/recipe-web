package com.uet.recipeweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.dto.LoginInfoDTO;
import com.uet.recipeweb.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserAPI {
	@Autowired
	IUserService userService;
	
	@GetMapping("/admin/users/search")
	@PreAuthorize("hasAuthority('admin')")
	public List<LoginInfoDTO> searchUser(String keyword) {
		System.out.println(keyword);
		return userService.findAllByUserNameContaining(keyword);
	}
	
	@PutMapping("/admin/users")
	@PreAuthorize("hasAuthority('admin')")
	public LoginInfoDTO setUserStatus(@RequestBody LoginInfoDTO model) {
		return userService.setUserStatus(model.getId(), model.getStatus());
	}
}
