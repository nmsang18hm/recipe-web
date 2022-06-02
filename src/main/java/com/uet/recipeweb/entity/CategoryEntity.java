package com.uet.recipeweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_group_id")
	private CategoryGroupEntity categoryGroupEntity;
	
	@ManyToMany(mappedBy = "categoryEntities")
	private List<RecipeEntity> recipeEntities = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryGroupEntity getCategoryGroupEntity() {
		return categoryGroupEntity;
	}

	public void setCategoryGroupEntity(CategoryGroupEntity categoryGroupEntity) {
		this.categoryGroupEntity = categoryGroupEntity;
	}

	public Integer getId() {
		return id;
	}

	public List<RecipeEntity> getRecipeEntities() {
		return recipeEntities;
	}

	public void setRecipeEntities(List<RecipeEntity> recipeEntities) {
		this.recipeEntities = recipeEntities;
	}
}
