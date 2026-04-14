package com.rajat.springboot.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajat.springboot.practice.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
