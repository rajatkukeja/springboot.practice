package com.rajat.springboot.practice.security;

import static org.springframework.security.config.Customizer.withDefaults;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.rajat.springboot.practice.filter.JWTTokenValidatorFilter;

@Configuration
public class AppSecurityConfig {
	
	@Autowired
	JWTTokenValidatorFilter filter;

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrfConfigur -> csrfConfigur.disable())
				.authorizeHttpRequests((requests) -> requests.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
						.requestMatchers(HttpMethod.POST , "/auth/**").permitAll()
						.requestMatchers(HttpMethod.POST , "/api/**").authenticated());
		http.addFilterBefore(filter, BasicAuthenticationFilter.class);
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}

	@Bean
	public UserDetailsService user() {
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return "admin";
			}
		});
		User user = new User("rajat", "$2a$10$Cv08YMgPjrgCpqhm4MxinOwxaugu0scpZUt0wZX.WlgbU2LFudGpG", list);
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public AuthenticationManager authManager() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(user());
		dao.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(dao);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
