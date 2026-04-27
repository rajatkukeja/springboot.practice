package com.rajat.springboot.practice.dto;

import java.util.ArrayList;
import java.util.List;

public class BlogDto {

	private int blogId;

	private String blogName;

	private String blogDescription;

	private List<CommentDto> commentList = new ArrayList<>();

	public BlogDto() {
	}

	public BlogDto(int blogId, String blogName, String blogDescription, List<CommentDto> commentList) {
		this.blogId = blogId;
		this.blogName = blogName;
		this.blogDescription = blogDescription;
		this.commentList = commentList;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public List<CommentDto> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentDto> commentList) {
		this.commentList = commentList;
	}

	

}
