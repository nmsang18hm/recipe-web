package com.uet.recipeweb.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class CollectionDTO {
	private Long id;
	private Long userId;
	private String userName;
	private String name;
	private String imageUrl;
	private Short isPublic;
	private Instant createTime;
	private Instant lastUpdate;
	private String updateBy;
	private List<Long> recipeIds = new ArrayList<>();
	private Integer totalLikes;
	
	final ZoneId vietnam = ZoneId.of("GMT+7");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Short isPublic) {
		this.isPublic = isPublic;
	}

	public ZonedDateTime getCreateTime() {
		return ZonedDateTime.ofInstant(createTime, vietnam);
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public ZonedDateTime getLastUpdate() {
		return ZonedDateTime.ofInstant(lastUpdate, vietnam);
	}

	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public List<Long> getRecipeIds() {
		return recipeIds;
	}

	public void setRecipeIds(List<Long> recipeIds) {
		this.recipeIds = recipeIds;
	}

	public Integer getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(Integer totalLikes) {
		this.totalLikes = totalLikes;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
