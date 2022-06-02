package com.uet.recipeweb.dto;

public class ReportUserDTO extends AbstractReportDTO {
	private Long reportUserId;
	private Long reportedUserId;

	public Long getReportedUserId() {
		return reportedUserId;
	}

	public void setReportedUserId(Long reportedUserId) {
		this.reportedUserId = reportedUserId;
	}

	public Long getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(Long reportUserId) {
		this.reportUserId = reportUserId;
	}
}
