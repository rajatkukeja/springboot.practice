package com.rajat.springboot.practice.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.rajat.springboot.practice.filter.JWTTokenValidatorFilter;

@Configuration
public class AppSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrfConfigur -> csrfConfigur.disable()).authorizeHttpRequests(
				(requests) -> requests.requestMatchers(HttpMethod.GET, "/api/**", "/logging/public").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/**").authenticated());
		http.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class);
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}

//	@Bean
//	public UserDetailsService user() {
//		List<GrantedAuthority> list = new ArrayList<>();
//		list.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				return "admin";
//			}
//		});
//		User user = new User("rajat", "$2a$10$Cv08YMgPjrgCpqhm4MxinOwxaugu0scpZUt0wZX.WlgbU2LFudGpG", list);
//		return new InMemoryUserDetailsManager(user);
//	}

	@Bean
	public AuthenticationManager authManager(AuthenticationProvider authProvider) {
//		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(user());
//		dao.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(authProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}
}
