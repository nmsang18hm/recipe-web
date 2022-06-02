package com.uet.recipeweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category_group")
public class CategoryGroupEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "categoryGroupEntity")
	private List<CategoryEntity> categoryEntities = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public List<CategoryEntity> getCategoryEntities() {
		return categoryEntities;
	}

	public void setCategoryEntities(List<CategoryEntity> categoryEntities) {
		this.categoryEntities = categoryEntities;
	}
}
