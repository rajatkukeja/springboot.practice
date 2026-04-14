package com.rajat.springboot.practice.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "blog")
@Table(name = "blog")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_id")
	private int blogId;

	@Column(name = "blog_name", nullable = false, length = 20)
	private String blogName;

	@Column(name = "blog_description", length = 50, nullable = false)
	private String blogDescription;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "blog")
	@JsonManagedReference
	private List<Comment> commentList = new ArrayList<>();

	public Blog() {
	}

	public Blog(int blogId, String blogName, String blogDescription, List<Comment> commentList) {
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
