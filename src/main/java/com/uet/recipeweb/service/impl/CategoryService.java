package com.uet.recipeweb.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uet.recipeweb.dto.CategoryDTO;
import com.uet.recipeweb.dto.CategoryGroupDTO;
import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.entity.CategoryEntity;
import com.uet.recipeweb.entity.CategoryGroupEntity;
import com.uet.recipeweb.entity.RecipeEntity;
import com.uet.recipeweb.repository.CategoryGroupRepository;
import com.uet.recipeweb.repository.CategoryRepository;
import com.uet.recipeweb.repository.LoginInfoRepository;
import com.uet.recipeweb.service.ICategoryService;
import com.uet.recipeweb.util.AwsUtils;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AwsUtils awsUtils;
	
	@Autowired
	CategoryGroupRepository categoryGroupRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	LoginInfoRepository loginInfoRepository;

	@Override
	public List<CategoryGroupDTO> findAllCategoryGroup() {
		List<CategoryGroupEntity> categoryGroupEntities = categoryGroupRepository.findAll();
		List<CategoryGroupDTO> categoryGroupDTOs = new ArrayList<>();
		for (CategoryGroupEntity categoryGroupEntity : categoryGroupEntities) {
			categoryGroupDTOs.add(modelMapper.map(categoryGroupEntity, CategoryGroupDTO.class));
		}
		return categoryGroupDTOs;
	}

	@Override
	public List<CategoryDTO> findAllCategoryByGroupId(Integer groupId) {
		List<CategoryEntity> categoryEntities = categoryGroupRepository.getById(groupId).getCategoryEntities();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (CategoryEntity categoryEntity : categoryEntities) {
			categoryDTOs.add(modelMapper.map(categoryEntity, CategoryDTO.class));
		}
		return categoryDTOs;
	}

	@Override
	public List<RecipeDTO> findAllRecipeByCategoryId(Integer categoryId) {
		List<RecipeEntity> recipeEntities = categoryRepository.getById(categoryId).getRecipeEntities();
		
		Iterator<RecipeEntity> iterator = recipeEntities.iterator();
		while (iterator.hasNext()) {
			RecipeEntity recipeEntity = iterator.next();
			if (recipeEntity.getIsAllowed() != 1) {
				iterator.remove();
			}
		}
		
		List<RecipeDTO> recipeDTOs = new ArrayList<>();
		for (RecipeEntity recipeEntity : recipeEntities) {
			RecipeDTO recipeDTO = modelMapper.map(recipeEntity, RecipeDTO.class);
			recipeDTO.setUser_name(loginInfoRepository.getById(recipeEntity.getOwner_id()).getUserName());
			recipeDTO.setMain_image_url(awsUtils.generateLink(recipeDTO.getMain_image_url()));
			recipeDTO.setTotal_likes(recipeEntity.getUserLikeEntities().size());
			recipeDTOs.add(recipeDTO);
		}
		return recipeDTOs;
	}

	@Override
	public List<RecipeDTO> findAllRecipeByGroupId(Integer groupId) {
		List<CategoryEntity> categoryEntities = categoryGroupRepository.getById(groupId).getCategoryEntities();
		List<RecipeEntity> recipeEntities = new ArrayList<>();
		for (CategoryEntity categoryEntity : categoryEntities) {
			recipeEntities.addAll(categoryEntity.getRecipeEntities());
		}
		
		Iterator<RecipeEntity> iterator = recipeEntities.iterator();
		while (iterator.hasNext()) {
			RecipeEntity recipeEntity = iterator.next();
			if (recipeEntity.getIsAllowed() != 1) {
				iterator.remove();
			}
		}
		
		List<RecipeDTO> recipeDTOs = new ArrayList<>();
		for (RecipeEntity recipeEntity : recipeEntities) {
			RecipeDTO recipeDTO = modelMapper.map(recipeEntity, RecipeDTO.class);
			recipeDTO.setUser_name(loginInfoRepository.getById(recipeEntity.getOwner_id()).getUserName());
			recipeDTO.setMain_image_url(awsUtils.generateLink(recipeDTO.getMain_image_url()));
			recipeDTO.setTotal_likes(recipeEntity.getUserLikeEntities().size());
			recipeDTOs.add(recipeDTO);
		}
		return recipeDTOs;
	}
	
}
