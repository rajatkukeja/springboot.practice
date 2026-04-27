package com.rajat.springboot.practice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.rajat.springboot.practice.dto.BlogDto;
import com.rajat.springboot.practice.dto.CommentDto;
import com.rajat.springboot.practice.entity.Blog;
import com.rajat.springboot.practice.entity.Comment;

public class EntityMapper {

	public static BlogDto mapToBlogDto(Blog blog) {
		List<Comment> list = blog.getCommentList();
		List<CommentDto> dtoList = list.stream().map(rec -> new CommentDto(rec.getCommentId(), rec.getDescription()))
				.collect(Collectors.toList());
		BlogDto blogDto = new BlogDto(blog.getBlogId(), blog.getBlogName(), blog.getBlogDescription(), dtoList);
		return blogDto;
	}

}
