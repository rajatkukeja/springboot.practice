package com.rajat.springboot.practice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.rajat.springboot.practice.dto.PostDto;

@Service
public class PostServiceImpl {

	@Autowired
	RestClient restClient;

	public List<PostDto> getPosts() {
		return restClient.get().uri("/posts").retrieve()
				.onStatus(HttpStatusCode::isError, (req, res) -> new IllegalArgumentException("Failed to fetch posts"))
				.body(new ParameterizedTypeReference<>() {
				});
	}
	public PostDto getPostById(int id) {
		return restClient.get().uri("/posts/{id}" , id)
				.retrieve()
				.onStatus(HttpStatusCode::isError, (req, res) -> new IllegalArgumentException("Failed to fetch post with id: " +id))
				.body(new ParameterizedTypeReference<>() {
				});
	}
	public PostDto create(PostDto postDto) {
		return restClient.post().uri("/posts")
				.body(postDto)
				.retrieve()
				.body(PostDto.class);
	}

}
