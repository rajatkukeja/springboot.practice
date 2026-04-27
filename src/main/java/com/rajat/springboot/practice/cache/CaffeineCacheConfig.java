package com.rajat.springboot.practice.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CaffeineCacheConfig {

	@Bean
	public CacheManager caffeineCacheManager() {

		CaffeineCache blogCache = new CaffeineCache("blogList",
				Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(5000).build());

		SimpleCacheManager manager = new SimpleCacheManager();
		List<CaffeineCache> list = new ArrayList<>();
		list.add(blogCache);
		manager.setCaches(list);
		return manager;
	}
}
