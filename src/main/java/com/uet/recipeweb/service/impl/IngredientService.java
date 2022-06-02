package com.uet.recipeweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uet.recipeweb.dto.IngredientDTO;
import com.uet.recipeweb.entity.IngredientEntity;
import com.uet.recipeweb.repository.IngredientRepository;
import com.uet.recipeweb.service.IIngredientService;

@Service
public class IngredientService implements IIngredientService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	IngredientRepository ingredientRepository;

	@Override
	public List<IngredientDTO> search(String keyword) {
		keyword = keyword.replaceAll("\\s","*");
		keyword = "*" + keyword + "*";
		List<IngredientEntity> ingredientEntities = ingredientRepository.search(keyword);
		List<IngredientDTO> ingredientDTOs = new ArrayList<>();
		for (IngredientEntity ingredientEntity : ingredientEntities) {
			ingredientDTOs.add(modelMapper.map(ingredientEntity, IngredientDTO.class));
		}
		return ingredientDTOs;
	}

}
