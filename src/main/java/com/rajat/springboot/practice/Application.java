package com.rajat.springboot.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.rajat.springboot.practice.props.AppProps;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(AppProps.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
