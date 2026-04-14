package com.rajat.springboot.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.springboot.practice.dto.LoginRequestDto;
import com.rajat.springboot.practice.dto.LoginResponseDto;
import com.rajat.springboot.practice.dto.UserDto;
import com.rajat.springboot.practice.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authManager;
	private JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
		try {
			Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequestDto.getUserName(), loginRequestDto.getUserName()));
			
			String jwtToken = jwtUtil.generateToken(authenticate);
			
			UserDto userDto = new UserDto();
			return new ResponseEntity<LoginResponseDto>(
					new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), userDto, jwtToken), HttpStatus.OK);
		} catch (BadCredentialsException badExce) {
			return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid UserName and Password");
		} catch (AuthenticationException authException) {
			return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication Failed");
		} catch (Exception ex) {
			return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An UnExpected error occured");
		}
	}

	private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
		return new ResponseEntity<LoginResponseDto>(new LoginResponseDto(message, null, null), status);
	}
}
