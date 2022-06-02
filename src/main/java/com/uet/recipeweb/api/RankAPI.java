package com.uet.recipeweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.dto.CollectionDTO;
import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.service.ICollectionService;
import com.uet.recipeweb.service.IRecipeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RankAPI {
	@Autowired
	IRecipeService recipeService;
	
	@Autowired
	ICollectionService collectionService;
	
	@GetMapping("/rank/recipes/week/top10view")
	public List<RecipeDTO> findTop10RecipeByViewWeek() {
		return recipeService.findTop10ByViewWeek();
	}
	
	@GetMapping("/rank/recipes/week/top10like")
	public List<RecipeDTO> findTop10RecipeByLikeWeek() {
		return recipeService.findTop10ByLikeWeek();
	}
	
	@GetMapping("/rank/recipes/month/top10view")
	public List<RecipeDTO> findTop10RecipeByViewMonth() {
		return recipeService.findTop10ByViewMonth();
	}
	
	@GetMapping("/rank/recipes/month/top10like")
	public List<RecipeDTO> findTop10RecipeByLikeMonth() {
		return recipeService.findTop10ByLikeMonth();
	}
	
	@GetMapping("/rank/collections/month/top10like")
	public List<CollectionDTO> findTop10CollectionByLikeMonth() {
		return collectionService.findTop10ByLikeMonth();
	}
	
	@GetMapping("/rank/recipes/top10new")
	public List<RecipeDTO> findTop10NewRecipe() {
		return recipeService.findTop10New();
	}
	
	@GetMapping("/rank/collections/top10new")
	public List<CollectionDTO> findTop10NewCollection() {
		return collectionService.findTop10New();
	}
	
	@GetMapping("/rank/collections/toplike")
	public List<CollectionDTO> findTopCollectionByLike(Long limit) {
		return collectionService.findAllOrderByLike(limit);
	}
}
