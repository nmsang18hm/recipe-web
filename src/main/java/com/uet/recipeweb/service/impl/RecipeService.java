package com.uet.recipeweb.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.entity.RecipeEntity;
import com.uet.recipeweb.repository.IngredientRepository;
import com.uet.recipeweb.repository.LoginInfoRepository;
import com.uet.recipeweb.repository.RecipeRepository;
import com.uet.recipeweb.service.IRecipeService;
import com.uet.recipeweb.util.AwsUtils;

@Service
public class RecipeService implements IRecipeService {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AwsUtils awsUtils;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	LoginInfoRepository loginInfoRepository;
	
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
		keyword = keyword.replaceAll("\\s","*");
		keyword = "*" + keyword + "*";
		List<RecipeEntity> recipeEntities = recipeRepository.search(keyword);
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
	public List<RecipeDTO> findTop10ByViewWeek() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByViewWeek();
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
	public List<RecipeDTO> findTop10ByLikeWeek() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByLikeWeek();
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
	public List<RecipeDTO> findTop10ByViewMonth() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByViewMonth();
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
	public List<RecipeDTO> findTop10ByLikeMonth() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10ByLikeMonth();
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
	public List<RecipeDTO> findTop10New() {
		List<RecipeEntity> recipeEntities = recipeRepository.findTop10New();
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
		
		List<RecipeDTO> recipeDTOs = new ArrayList<>();
		for (RecipeEntity recipeEntity : resultEntities) {
			RecipeDTO recipeDTO = modelMapper.map(recipeEntity, RecipeDTO.class);
			recipeDTO.setUser_name(loginInfoRepository.getById(recipeEntity.getOwner_id()).getUserName());
			recipeDTO.setMain_image_url(awsUtils.generateLink(recipeDTO.getMain_image_url()));
			recipeDTO.setTotal_likes(recipeEntity.getUserLikeEntities().size());
			recipeDTOs.add(recipeDTO);
		}
		return recipeDTOs;
	}

	@Override
	public List<RecipeDTO> findAllByIsAllowedFalse() {
		List<RecipeEntity> recipeEntities = recipeRepository.findAllByIsAllowed((short) 0);
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
