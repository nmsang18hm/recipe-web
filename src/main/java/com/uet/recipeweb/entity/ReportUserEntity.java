package com.uet.recipeweb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_report_user")
public class ReportUserEntity extends AbstractReportEntity {
	
	@ManyToOne
	@JoinColumn(name = "report_user_id")
	private UserEntity reportUser;
	
	@ManyToOne
	@JoinColumn(name = "reported_user_id")
	private UserEntity reportedUser;

	public UserEntity getReportUser() {
		return reportUser;
	}

	public void setReportUser(UserEntity reportUser) {
		this.reportUser = reportUser;
	}

	public UserEntity getReportedUser() {
		return reportedUser;
	}

	public void setReportedUser(UserEntity reportedUser) {
		this.reportedUser = reportedUser;
	}
}
