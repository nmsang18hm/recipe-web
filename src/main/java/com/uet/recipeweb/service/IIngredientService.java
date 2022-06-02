package com.uet.recipeweb.service;

import java.util.List;

import com.uet.recipeweb.dto.IngredientDTO;

public interface IIngredientService {
	public List<IngredientDTO> search(String keyword);
}
