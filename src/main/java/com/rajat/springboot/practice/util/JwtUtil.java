package com.rajat.springboot.practice.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${app.secret.key}")
	private String key;

	public String generateToken(Authentication authentication) {
		String jwtToken = null;
		SecretKey secreyKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
		String user = (String) authentication.getName();
		jwtToken = Jwts.builder().issuer("Rajat kukreja").subject("JWT Token").claim("username", user)
				.claim("role",
						authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
								.collect(Collectors.joining(",")))
				.issuedAt(new Date()).expiration(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
				.signWith(secreyKey).compact();
		return jwtToken;

	}
}
