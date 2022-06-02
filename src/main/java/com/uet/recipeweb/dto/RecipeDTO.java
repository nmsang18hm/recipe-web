package com.uet.recipeweb.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class RecipeDTO {
	
	// NOTICE
	// I knew and wanted to use camel case.
	// But the team members worked first. So I had to use their convention.
	
	private Long id;
	private String title;	
	private String main_image_url;
	private Long owner_id;
	private String user_name;
	private Integer total_views;
	private Integer total_likes;
	private Short amount_of_people;
	private Integer cooking_time;
	private Instant create_time;
	private Instant last_update;
	
	final ZoneId vietnam = ZoneId.of("GMT+7");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMain_image_url() {
		return main_image_url;
	}

	public void setMain_image_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getTotal_views() {
		return total_views;
	}

	public void setTotal_views(Integer total_views) {
		this.total_views = total_views;
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

	public Long getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}

	public ZonedDateTime getCreate_time() {
		return ZonedDateTime.ofInstant(create_time, vietnam);
	}

	public void setCreate_time(Instant create_time) {
		this.create_time = create_time;
	}

	public ZonedDateTime getLast_update() {
		return ZonedDateTime.ofInstant(last_update, vietnam);
	}

	public void setLast_update(Instant last_update) {
		this.last_update = last_update;
	}

	public Integer getTotal_likes() {
		return total_likes;
	}

	public void setTotal_likes(Integer total_likes) {
		this.total_likes = total_likes;
	}
	
}
