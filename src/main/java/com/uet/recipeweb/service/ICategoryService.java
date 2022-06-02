package com.uet.recipeweb.service;

import java.util.List;

import com.uet.recipeweb.dto.CategoryDTO;
import com.uet.recipeweb.dto.CategoryGroupDTO;
import com.uet.recipeweb.dto.RecipeDTO;

public interface ICategoryService {
	public List<CategoryGroupDTO> findAllCategoryGroup();
	public List<CategoryDTO> findAllCategoryByGroupId(Integer groupId);
	public List<RecipeDTO> findAllRecipeByCategoryId(Integer categoryId);
	public List<RecipeDTO> findAllRecipeByGroupId(Integer groupId);
}
