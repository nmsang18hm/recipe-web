package com.uet.recipeweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.service.IRecipeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RecipeAPI {
	@Autowired
	IRecipeService recipeService;
	
	@GetMapping("/recipes/isallowedfalse")
	@PreAuthorize("hasAuthority('admin')")
	public List<RecipeDTO> findAllByIsAllowedFalse() {
		return recipeService.findAllByIsAllowedFalse();
	}
}
