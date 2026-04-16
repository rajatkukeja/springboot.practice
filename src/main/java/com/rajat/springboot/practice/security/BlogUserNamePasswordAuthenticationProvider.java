package com.rajat.springboot.practice.security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rajat.springboot.practice.entity.AppUser;
import com.rajat.springboot.practice.repository.AppUserRepository;

@Component
public class BlogUserNamePasswordAuthenticationProvider implements AuthenticationProvider {

	private AppUserRepository appUserRepo;
	private PasswordEncoder passwordEncoder;

	public BlogUserNamePasswordAuthenticationProvider(AppUserRepository appUserRepo, PasswordEncoder passwordEncoder) {
		this.appUserRepo = appUserRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		AppUser appUser = appUserRepo.findAppUserByName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User details not found for the user"));

		List<SimpleGrantedAuthority> roleList = List.of(new SimpleGrantedAuthority(appUser.getRoles().getName()));

		if (passwordEncoder.matches(password, appUser.getPasswordHash())) {
			return new UsernamePasswordAuthenticationToken(appUser.getName(), null, roleList);
		} else {
			throw new BadCredentialsException("Invalid password");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
