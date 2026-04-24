package com.rajat.springboot.practice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rajat.springboot.practice.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	// Fixing N+1 Query issue of hibernate
	// Adding caching
	@Cacheable("blogList")
	@Query("SELECT DISTINCT b FROM blog b JOIN FETCH b.commentList")
	public List<Blog> findBlogList();

	@CacheEvict(value = "blogList", allEntries = true)
	Optional<Blog> findById(int id);

}
