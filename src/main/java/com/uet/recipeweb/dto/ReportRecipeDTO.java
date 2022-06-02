package com.uet.recipeweb.dto;

public class ReportRecipeDTO extends AbstractReportDTO {
	private Long userId;
	private Long recipeId;

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
