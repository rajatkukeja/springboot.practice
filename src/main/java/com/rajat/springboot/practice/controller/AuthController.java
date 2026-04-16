package com.rajat.springboot.practice.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.springboot.practice.dto.LoginRequestDto;
import com.rajat.springboot.practice.dto.LoginResponseDto;
import com.rajat.springboot.practice.dto.RegisterRequestDto;
import com.rajat.springboot.practice.dto.UserDto;
import com.rajat.springboot.practice.entity.AppUser;
import com.rajat.springboot.practice.entity.Roles;
import com.rajat.springboot.practice.repository.AppUserRepository;
import com.rajat.springboot.practice.repository.RoleRepository;
import com.rajat.springboot.practice.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authManager;
	private JwtUtil jwtUtil;
	private AppUserRepository appUserRepo;
	private RoleRepository roleRepo;
	private CompromisedPasswordChecker checker;

	public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder,
			RoleRepository roleRepo, AppUserRepository appUserRepo, CompromisedPasswordChecker checker) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
		this.roleRepo = roleRepo;
		this.appUserRepo = appUserRepo;
		this.checker = checker;

	}

	/*
	 * Method to register the user into the Database
	 */
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
		// Logic to check if you are registering yourself with week password.
		CompromisedPasswordDecision decision = checker.check(registerRequestDto.getPassword());
		if (decision.isCompromised()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of("Password", "Please use stringt password"));
		}

		// Fetching App Users from the DB with the help of email and mobile number

		Optional<AppUser> registeredAppUser = appUserRepo.findByEmailAndMobileNumber(registerRequestDto.getEmail(),
				registerRequestDto.getMobileNumber());

		// Checking if user has already registered or not
		if (registeredAppUser.isPresent()) {
			Map<String, String> errors = new HashMap<>();
			AppUser appUser = registeredAppUser.get();
			if (appUser.getEmail().equalsIgnoreCase(registerRequestDto.getEmail())) {
				errors.put("Email", "Email is already registered");
			}
			if (appUser.getMobileNumber().equals(registerRequestDto.getMobileNumber())) {
				errors.put("Mobile Number", "Mobile number has already been registerd");
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		// If not Saving the record in the database.
		AppUser appUser = new AppUser();
		BeanUtils.copyProperties(registerRequestDto, appUser);
		appUser.setPasswordHash(passwordEncoder.encode(registerRequestDto.getPassword()));
		Roles roles = roleRepo.findByName("ROLE_BLOG_READER")
				.orElseThrow(() -> new IllegalArgumentException("Role not found : " + "ROLE_BLOG_READER"));
		appUser.setCreatedBy(roles.getCreatedBy());
		appUser.setRoles(roles);
		appUserRepo.save(appUser);
		return new ResponseEntity<String>("User has been saved into the database successfully", HttpStatus.CREATED);
	}

	/*
	 * API to login to the user and share JWT token if all good.
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

		try {
			Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequestDto.getUserName(), loginRequestDto.getPassword()));

			String jwtToken = jwtUtil.generateToken(authenticate);

			UserDto userDto = new UserDto();
//			AppUser appUser = (AppUser) authenticate.getDetails();
//			BeanUtils.copyProperties(appUser, userDto);
//			userDto.setRole(appUser.getRoles().getName());

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
