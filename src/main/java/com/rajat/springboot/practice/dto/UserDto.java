package com.rajat.springboot.practice.dto;

import java.time.Instant;

public class UserDto {

	private Long userId;

	private String name;

	private String email;

	private String mobileNumber;

	private String role;

	private Long companyId;

	private String companyName;

	private Instant createdAt;

	public UserDto() {
	}

	public UserDto(Long userId, String name, String email, String mobileNumber, String role, Long companyId,
			String companyName, Instant createdAt) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.companyId = companyId;
		this.companyName = companyName;
		this.createdAt = createdAt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

}
