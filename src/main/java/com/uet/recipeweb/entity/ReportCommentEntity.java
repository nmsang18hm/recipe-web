package com.uet.recipeweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_report_comment")
public class ReportCommentEntity extends AbstractReportEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userReportComment;
	
	@Column(name = "comment_id")
	private Long commentId;

	public UserEntity getUserReportComment() {
		return userReportComment;
	}

	public void setUserReportComment(UserEntity userReportComment) {
		this.userReportComment = userReportComment;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
}
