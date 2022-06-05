package com.uet.recipeweb.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uet.recipeweb.dto.CollectionDTO;
import com.uet.recipeweb.dto.RecipeDTO;
import com.uet.recipeweb.entity.CollectionEntity;
import com.uet.recipeweb.entity.RecipeEntity;
import com.uet.recipeweb.repository.LoginInfoRepository;

@Component
public class MapperUtils {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AwsUtils awsUtils;
	
	@Autowired
	LoginInfoRepository loginInfoRepository;
	
	public List<CollectionDTO> mapCollectionEntityListToDTO(List<CollectionEntity> collectionEntities) {
		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		for (CollectionEntity collectionEntity : collectionEntities) {
			CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
			collectionDTO.setUserId(collectionEntity.getOwner().getId());
			collectionEntity.getRecipeEntities().forEach(recipe -> {
				collectionDTO.getRecipeIds().add(recipe.getId());
			});
			collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
			collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
			if (collectionEntity.getRecipeEntities().size() > 0) {
				collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
			}
			collectionDTOs.add(collectionDTO);
		}
		return collectionDTOs;
	}
	
	public List<RecipeDTO> mapRecipeEntityListToDTO(List<RecipeEntity> recipeEntities) {
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
