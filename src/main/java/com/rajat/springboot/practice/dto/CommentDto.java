package com.rajat.springboot.practice.dto;

import com.rajat.springboot.practice.entity.Blog;

public class CommentDto {

	private int commentId;

	private String description;

	private Blog blog;

	public CommentDto() {
	}

	public CommentDto(int commentId, String description, Blog blog) {
		this.commentId = commentId;
		this.description = description;
		this.blog = blog;
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

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

}
