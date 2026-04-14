package com.rajat.springboot.practice.mapper;

import com.rajat.springboot.practice.dto.BlogDto;
import com.rajat.springboot.practice.entity.Blog;

public class EntityMapper {

	public static BlogDto mapToBlogDto(Blog blog) {
		BlogDto blogDto = new BlogDto(blog.getBlogId(), blog.getBlogName(), blog.getBlogDescription(), blog.getCommentList());
		return blogDto;
	}

}
