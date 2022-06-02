package com.uet.recipeweb.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class RecipeEntity {
	
	// NOTICE
	// I knew and wanted to use camel case.
	// But the team members worked first. So I had to use their convention.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column(name = "short_description")
	private String short_description;
	
	@Column(name = "main_image_url")
	private String main_image_url;
	
	@Column(name = "owner_id")
	private Long owner_id;
	
	@Column(name = "total_views")
	private Integer total_views;
	
	@Column(name = "is_allowed")
	private Short isAllowed;
	
	@Column(name = "amount_of_people")
	private Short amount_of_people;
	
	@Column(name = "cooking_time")
	private Integer cooking_time;
	
	@Column(name = "create_time")
	private Instant create_time;
	
	@Column(name = "last_update")
	private Instant last_update;
	
	@ManyToMany
	@JoinTable(name = "collection_has_recipe",
					joinColumns = @JoinColumn(name = "recipe_id"),
					inverseJoinColumns = @JoinColumn(name = "collection_id"))
	private List<CollectionEntity> collectionEntities = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "category_has_recipe",
					joinColumns = @JoinColumn(name = "recipe_id"),
					inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CategoryEntity> categoryEntities = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "recipe_has_ingredient",
					joinColumns = @JoinColumn(name = "recipe_id"),
					inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<IngredientEntity> ingredientEntities = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "`like`",
					joinColumns = @JoinColumn(name = "recipe_id"),
					inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserEntity> userLikeEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "reportedRecipe")
	private List<ReportRecipeEntity> reportRecipeEntities = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getMain_image_url() {
		return main_image_url;
	}

	public void setMain_image_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}

	public Long getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}

	public Integer getTotal_views() {
		return total_views;
	}

	public void setTotal_views(Integer total_views) {
		this.total_views = total_views;
	}

	public Short getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Short isAllowed) {
		this.isAllowed = isAllowed;
	}

	public Short getAmount_of_people() {
		return amount_of_people;
	}

	public void setAmount_of_people(Short amount_of_people) {
		this.amount_of_people = amount_of_people;
	}

	public Integer getCooking_time() {
		return cooking_time;
	}

	public void setCooking_time(Integer cooking_time) {
		this.cooking_time = cooking_time;
	}

	public List<CollectionEntity> getCollectionEntities() {
		return collectionEntities;
	}

	public void setCollectionEntities(List<CollectionEntity> collectionEntities) {
		this.collectionEntities = collectionEntities;
	}

	public Long getId() {
		return id;
	}

	public List<CategoryEntity> getCategoryEntities() {
		return categoryEntities;
	}

	public void setCategoryEntities(List<CategoryEntity> categoryEntities) {
		this.categoryEntities = categoryEntities;
	}

	public Instant getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Instant create_time) {
		this.create_time = create_time;
	}

	public Instant getLast_update() {
		return last_update;
	}

	public void setLast_update(Instant last_update) {
		this.last_update = last_update;
	}

	public List<IngredientEntity> getIngredientEntities() {
		return ingredientEntities;
	}

	public void setIngredientEntities(List<IngredientEntity> ingredientEntities) {
		this.ingredientEntities = ingredientEntities;
	}
	
	public List<UserEntity> getUserLikeEntities() {
		return userLikeEntities;
	}

	public void setUserLikeEntities(List<UserEntity> userLikeEntities) {
		this.userLikeEntities = userLikeEntities;
	}

	public List<ReportRecipeEntity> getReportRecipeEntities() {
		return reportRecipeEntities;
	}

	public void setReportRecipeEntities(List<ReportRecipeEntity> reportRecipeEntities) {
		this.reportRecipeEntities = reportRecipeEntities;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RecipeEntity) {
			RecipeEntity recipeEntity = (RecipeEntity) obj;
			if (this.id == recipeEntity.getId()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
