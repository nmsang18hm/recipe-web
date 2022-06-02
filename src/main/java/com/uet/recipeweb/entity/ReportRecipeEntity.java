package com.uet.recipeweb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_report_recipe")
public class ReportRecipeEntity extends AbstractReportEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userReportRecipe;
	
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private RecipeEntity reportedRecipe;

	public UserEntity getUserReportRecipe() {
		return userReportRecipe;
	}

	public void setUserReportRecipe(UserEntity userReportRecipe) {
		this.userReportRecipe = userReportRecipe;
	}

	public RecipeEntity getReportedRecipe() {
		return reportedRecipe;
	}

	public void setReportedRecipe(RecipeEntity reportedRecipe) {
		this.reportedRecipe = reportedRecipe;
	}
}
