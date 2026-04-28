package com.rajat.springboot.practice;

import java.net.http.HttpClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import com.rajat.springboot.practice.props.AppProps;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(AppProps.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestClient restClient() {
		return RestClient.builder().baseUrl("http://jsonplaceholder.typicode.com")
				.defaultHeader("Accept", "application/json").build();
	}

}
