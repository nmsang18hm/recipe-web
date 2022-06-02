package com.uet.recipeweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.dto.CollectionDTO;
import com.uet.recipeweb.dto.IngredientDTO;
import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.service.ICollectionService;
import com.uet.recipeweb.service.IIngredientService;
import com.uet.recipeweb.service.IRecipeService;
import com.uet.recipeweb.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SearchAPI {
	@Autowired
	IRecipeService recipeService;
	
	@Autowired
	ICollectionService collectionService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IIngredientService ingredientService;
	
	@GetMapping("/search/suggestion")
	public List<String> suggest(String keyword) {
		List<String> result = recipeService.suggestRecipeName(keyword);
		result.addAll(collectionService.suggestCollectionName(keyword));
		//result.addAll(userService.suggestUserFullName(keyword));
		return result;
	}
	
	@GetMapping("/search/recipes")
	public List<RecipeDTO> searchRecipe(String keyword) {
		return recipeService.search(keyword);
	}
	
	@GetMapping("/search/collections")
	public List<CollectionDTO> searchCollection(String keyword) {
		return collectionService.search(keyword);
	}
	
	@GetMapping("/search/ingredients")
	public List<IngredientDTO> searchIngredient(String keyword) {
		return ingredientService.search(keyword);
	}
	
	@GetMapping("/search/recipes/ingredients")
	public List<RecipeDTO> searchRecipeByIngredients(Integer[] ingredientIds) {
		return recipeService.findByIngredientIds(ingredientIds);
	}
}
