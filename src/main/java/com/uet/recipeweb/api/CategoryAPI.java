package com.uet.recipeweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.dto.CategoryDTO;
import com.uet.recipeweb.dto.CategoryGroupDTO;
import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.service.ICategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CategoryAPI {
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/categories/groups")
	public List<CategoryGroupDTO> findAllGroups() {
		return categoryService.findAllCategoryGroup();
	}
	
	@GetMapping("/categories/groups/{groupId}")
	public List<CategoryDTO> findAllCategoryByGroupId(@PathVariable("groupId") Integer groupId) {
		return categoryService.findAllCategoryByGroupId(groupId);
	}
	
	@GetMapping("/categories/{categoryId}")
	public List<RecipeDTO> findAllRecipeByCategoryId(@PathVariable("categoryId") Integer categoryId) {
		return categoryService.findAllRecipeByCategoryId(categoryId);
	}
	
	@GetMapping("/categories/recipes/groups/{groupId}")
	public List<RecipeDTO> findAllRecipeByGroupId(@PathVariable("groupId") Integer groupId) {
		return categoryService.findAllRecipeByGroupId(groupId);
	}
}
