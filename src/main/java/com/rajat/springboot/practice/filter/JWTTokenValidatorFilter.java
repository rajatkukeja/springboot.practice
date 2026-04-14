package com.rajat.springboot.practice.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

	public static final String JWT_HEADER = "Authorization";

	@Value("${app.secret.key}")
	private String key;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader(JWT_HEADER);

		if (null != authHeader) {
			// Fetch JWT Token
			try {
				String jwt = authHeader.substring(7); // Extract token value from bearer header
				SecretKey secretKey = Keys
						.hmacShaKeyFor(key
								.getBytes(StandardCharsets.UTF_8));
				if (null != secretKey) {
					Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload();
					String username = String.valueOf(claims.get("username"));
					String roles = String.valueOf(claims.get("role"));
					Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
							AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (ExpiredJwtException jwtExpired) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Token Expired");
				return;
			} catch (Exception exception) {
				throw new BadCredentialsException("Invalid Token received!");
			}
		}

		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return "POST".equalsIgnoreCase(request.getMethod()) && requestURI.startsWith("/auth");
	}

}
