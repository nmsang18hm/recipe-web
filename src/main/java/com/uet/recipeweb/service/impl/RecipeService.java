package com.uet.recipeweb.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.entity.RecipeEntity;
import com.uet.recipeweb.repository.IngredientRepository;
import com.uet.recipeweb.repository.RecipeRepository;
import com.uet.recipeweb.service.IRecipeService;
import com.uet.recipeweb.util.MapperUtils;

@Service
public class RecipeService implements IRecipeService {
	
	@Autowired
	MapperUtils mapperUtils;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Override
	public List<String> suggestRecipeName(String keyword) {
		keyword = keyword.replaceAll("\\s","*");
		keyword = "*" + keyword + "*";
		return recipeRepository.suggestRecipeName(keyword);
	}

	@Override
	public List<RecipeDTO> search(String keyword) {
		//keyword = keyword.replaceAll("\\s","*");
		//keyword = "*" + keyword + "*";
		List<RecipeEntity> recipeEntities = recipeRepository.search(keyword);
		return mapperUtils.mapRecipeEntityListToDTO(recipeEntities);
	}

	@Override
	public List<RecipeDTO> findTop10ByViewWeek() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByViewWeek();
		return mapperUtils.mapRecipeEntityListToDTO(recipeEntities);
	}

	@Override
	public List<RecipeDTO> findTop10ByLikeWeek() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByLikeWeek();
		return mapperUtils.mapRecipeEntityListToDTO(recipeEntities);
	}

	@Override
	public List<RecipeDTO> findTop10ByViewMonth() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByViewMonth();
		return mapperUtils.mapRecipeEntityListToDTO(recipeEntities);
	}

	@Override
	public List<RecipeDTO> findTop10ByLikeMonth() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByLikeMonth();
		return mapperUtils.mapRecipeEntityListToDTO(recipeEntities);

	}

	@Override
	public List<RecipeDTO> findTop10New() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10New();
		return mapperUtils.mapRecipeEntityListToDTO(recipeEntities);
	}

	@Override
	public List<RecipeDTO> findByIngredientIds(Integer[] ingredientIds) {
		List<RecipeEntity> resultEntities = ingredientRepository.getById(ingredientIds[0]).getRecipeEntities();
		for (int i = 1; i < ingredientIds.length; i++) {
			List<RecipeEntity> recipeEntities = ingredientRepository.getById(ingredientIds[i]).getRecipeEntities();
			Iterator<RecipeEntity> iterator = resultEntities.iterator();
			while (iterator.hasNext()) {
				RecipeEntity recipeEntity = iterator.next();
				if (!recipeEntities.contains(recipeEntity)) {
					iterator.remove();
				}
			}
		}
		
		Iterator<RecipeEntity> iterator = resultEntities.iterator();
		while (iterator.hasNext()) {
			RecipeEntity recipeEntity = iterator.next();
			if (recipeEntity.getIsAllowed() != 1) {
				iterator.remove();
			}
		}
		
		return mapperUtils.mapRecipeEntityListToDTO(resultEntities);
	}

	@Override
	public List<RecipeDTO> findAllByIsAllowedFalse() {
		List<RecipeEntity> recipeEntities = recipeRepository.findAllByIsAllowed((short) 0);
		return mapperUtils.mapRecipeEntityListToDTO(recipeEntities);
	}
}
