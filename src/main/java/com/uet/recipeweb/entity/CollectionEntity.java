package com.uet.recipeweb.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "collection")
@EntityListeners(AuditingEntityListener.class)
public class CollectionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column(name = "is_public")
	private Short isPublic;
	
	@Column(name = "create_time")
	@CreatedDate
	private Instant createTime;
	
	@Column(name = "last_update")
	@LastModifiedDate
	private Instant lastUpdate;
	
	@Column(name = "update_by")
	@LastModifiedBy
	private String updateBy;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity owner;
	
	@ManyToMany(mappedBy = "collectionEntities")
	private List<RecipeEntity> recipeEntities = new ArrayList<>();
	
	@ManyToMany(mappedBy = "savedCollections")
	private List<UserEntity> userSaveEntities = new ArrayList<>();

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

	public Instant getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public Instant getLastUpdate() {
		return lastUpdate;
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

	public Long getId() {
		return id;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<RecipeEntity> getRecipeEntities() {
		return recipeEntities;
	}

	public void setRecipeEntities(List<RecipeEntity> recipeEntities) {
		this.recipeEntities = recipeEntities;
	}

	public List<UserEntity> getUserSaveEntities() {
		return userSaveEntities;
	}

	public void setUserSaveEntities(List<UserEntity> userSaveEntities) {
		this.userSaveEntities = userSaveEntities;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CollectionEntity) {
			CollectionEntity collectionEntity = (CollectionEntity) obj;
			if (this.id == collectionEntity.getId()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
