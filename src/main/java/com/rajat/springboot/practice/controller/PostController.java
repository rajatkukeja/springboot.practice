package com.rajat.springboot.practice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.springboot.practice.dto.PostDto;
import com.rajat.springboot.practice.serviceimpl.PostServiceImpl;

@RestController
@RequestMapping("/callexternalapis")
public class PostController {

	private PostServiceImpl postServiceImpl;

	public PostController(PostServiceImpl postServiceImpl) {
		this.postServiceImpl = postServiceImpl;
	}

	@GetMapping
	public List<PostDto> getPosts() {
		return postServiceImpl.getPosts();
	}
	@GetMapping("/{id}")
	public PostDto getPosts(@PathVariable("id") int id) {
		return postServiceImpl.getPostById(id);
	}
	@PostMapping
	public PostDto create() {
		return postServiceImpl.create();
	}
}
