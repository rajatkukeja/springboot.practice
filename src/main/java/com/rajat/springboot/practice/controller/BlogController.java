package com.rajat.springboot.practice.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.springboot.practice.dto.BlogDto;
import com.rajat.springboot.practice.entity.Blog;
import com.rajat.springboot.practice.service.BlogService;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

	private BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping
	public ResponseEntity<List<BlogDto>> getBlogList() {
		List<BlogDto> blogList = blogService.getBlogList();
		return new ResponseEntity<List<BlogDto>>(blogList, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createPost(@RequestBody Blog blog) {
		boolean isPosted = false;
		isPosted = blogService.createPost(blog);

		if (isPosted) {
			return new ResponseEntity<String>(
					"This request has been processed, and your record has been saved successfully in the database",
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Something went wrong.. Please try again later!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pagination")
	public Page<Blog> getPaginationAndSortedBlog(@RequestParam(required = false , defaultValue = "0") int pageNo,
			@RequestParam(required = false , defaultValue = "10") int pageSize,
			@RequestParam(required = false , defaultValue = "blogName") String fieldName) {
		Page<Blog> blogPage = blogService.getPaginationAndSortedBlog(pageNo, pageSize, fieldName);
		return blogPage;
	}

}
