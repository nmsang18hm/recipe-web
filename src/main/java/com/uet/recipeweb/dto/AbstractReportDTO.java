package com.uet.recipeweb.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AbstractReportDTO {
	private Integer id;
	private String content;
	private Short status;
	private String response;
	private Instant createTime;
	private Instant lastUpdate;

	final ZoneId vietnam = ZoneId.of("GMT+7");

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
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
}
