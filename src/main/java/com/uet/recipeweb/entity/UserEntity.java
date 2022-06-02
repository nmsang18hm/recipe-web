package com.uet.recipeweb.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "user")
public class UserEntity {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column
	private String introduction;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column
	private Short gender;
	
	@Column
	private String city;
	
	@Column
	private String district;
	
	@Column(name = "avatar_image")
	private String avatarImage;
	
	@Column(name = "cover_image")
	private String coverImage;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "last_update")
	private Date lastUpdate;
	
	@OneToMany(mappedBy = "owner")
	private List<CollectionEntity> myCollections = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "user_save_collection",
					joinColumns = @JoinColumn(name = "user_id"),
					inverseJoinColumns = @JoinColumn(name = "collection_id"))
	private List<CollectionEntity> savedCollections = new ArrayList<>();
	
	@ManyToMany(mappedBy = "userLikeEntities")
	private List<RecipeEntity> likedRecipeEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "userReportRecipe")
	private List<ReportRecipeEntity> reportRecipeEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "userReportComment")
	private List<ReportCommentEntity> reportCommentEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "reportUser")
	private List<ReportUserEntity> reportUserEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "reportedUser")
	private List<ReportUserEntity> reportedUserEntities = new ArrayList<>();

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<CollectionEntity> getMyCollections() {
		return myCollections;
	}

	public void setMyCollections(List<CollectionEntity> myCollections) {
		this.myCollections = myCollections;
	}

	public List<CollectionEntity> getSavedCollections() {
		return savedCollections;
	}

	public void setSavedCollections(List<CollectionEntity> savedCollections) {
		this.savedCollections = savedCollections;
	}

	public Long getId() {
		return id;
	}

	public List<RecipeEntity> getLikedRecipeEntities() {
		return likedRecipeEntities;
	}

	public void setLikedRecipeEntities(List<RecipeEntity> likedRecipeEntities) {
		this.likedRecipeEntities = likedRecipeEntities;
	}

	public List<ReportRecipeEntity> getReportRecipeEntities() {
		return reportRecipeEntities;
	}

	public void setReportRecipeEntities(List<ReportRecipeEntity> reportRecipeEntities) {
		this.reportRecipeEntities = reportRecipeEntities;
	}

	public List<ReportCommentEntity> getReportCommentEntities() {
		return reportCommentEntities;
	}

	public void setReportCommentEntities(List<ReportCommentEntity> reportCommentEntities) {
		this.reportCommentEntities = reportCommentEntities;
	}

	public List<ReportUserEntity> getReportUserEntities() {
		return reportUserEntities;
	}

	public void setReportUserEntities(List<ReportUserEntity> reportUserEntities) {
		this.reportUserEntities = reportUserEntities;
	}

	public List<ReportUserEntity> getReportedUserEntities() {
		return reportedUserEntities;
	}

	public void setReportedUserEntities(List<ReportUserEntity> reportedUserEntities) {
		this.reportedUserEntities = reportedUserEntities;
	}
}
