package com.uet.recipeweb.service;

import java.util.List;

import com.uet.recipeweb.dto.RecipeDTO;

public interface IRecipeService {
	public List<String> suggestRecipeName(String keyword);
	public List<RecipeDTO> search(String keyword);
	public List<RecipeDTO> findTop10ByViewWeek();
	public List<RecipeDTO> findTop10ByLikeWeek();
	public List<RecipeDTO> findTop10ByViewMonth();
	public List<RecipeDTO> findTop10ByLikeMonth();
	public List<RecipeDTO> findTop10New();
	public List<RecipeDTO> findByIngredientIds(Integer[] ingredientIds);
	public List<RecipeDTO> findAllByIsAllowedFalse();
}
