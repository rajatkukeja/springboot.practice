package com.rajat.springboot.practice.dto;


public class CommentDto {

	private int commentId;

	private String description;


	public CommentDto() {
	}

	public CommentDto(int commentId, String description) {
		this.commentId = commentId;
		this.description = description;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
