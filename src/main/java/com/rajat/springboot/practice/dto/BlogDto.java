package com.rajat.springboot.practice.dto;

import java.util.ArrayList;
import java.util.List;
import com.rajat.springboot.practice.entity.Comment;

public class BlogDto {

	private int blogId;

	private String blogName;

	private String blogDescription;

	private List<Comment> commentList = new ArrayList<>();

	public BlogDto() {
	}

	public BlogDto(int blogId, String blogName, String blogDescription, List<Comment> commentList) {
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

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
