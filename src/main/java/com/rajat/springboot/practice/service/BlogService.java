package com.rajat.springboot.practice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rajat.springboot.practice.dto.BlogDto;
import com.rajat.springboot.practice.entity.Blog;

public interface BlogService {

	List<BlogDto> getBlogList();

	boolean createPost(Blog blog);

	Page<Blog> getPaginationAndSortedBlog(int pageNo, int pageSize, String fieldName);

}
