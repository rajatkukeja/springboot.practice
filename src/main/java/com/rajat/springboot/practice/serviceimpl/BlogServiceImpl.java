package com.rajat.springboot.practice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.rajat.springboot.practice.dto.BlogDto;
import com.rajat.springboot.practice.entity.Blog;
import com.rajat.springboot.practice.entity.Comment;
import com.rajat.springboot.practice.mapper.EntityMapper;
import com.rajat.springboot.practice.repository.BlogRepository;
import com.rajat.springboot.practice.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogRepository blogRepo;

	public BlogServiceImpl(BlogRepository blogRepo) {
		this.blogRepo = blogRepo;
	}

	@Override
	public List<BlogDto> getBlogList() {
		List<BlogDto> blogDtoList= new ArrayList<>();
		List<Blog> blogList = blogRepo.findAll();
		blogDtoList = blogList.stream().map(blog -> EntityMapper.mapToBlogDto(blog)).collect(Collectors.toList());
		return blogDtoList;
	}

	@Override
	public boolean createPost(Blog blog) {
		List<Comment> commentList = new ArrayList<>();
		for(Comment comment : blog.getCommentList()) {
			comment.setDescription(comment.getDescription());
			comment.setBlog(blog);
			commentList.add(comment);
		}
		blog.setCommentList(commentList);
		Blog savedBlog = blogRepo.save(blog);
		return savedBlog.getBlogId() >= 1 ? true : false;
	}

	@Override
	public Page<Blog> getPaginationAndSortedBlog(int pageNo, int pageSize, String fieldName) {
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize , Sort.by(Order.asc(fieldName)));
		Page<Blog> page = blogRepo.findAll(pageRequest);
		return page;
	}

}
