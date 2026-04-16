package com.rajat.springboot.practice.dto;

public class RegisterRequestDto {

	private String name;

	private String email;

	private String mobileNumber;
	
	private String password;

	public RegisterRequestDto() {
	}

	public RegisterRequestDto(String name, String email, String mobileNumber , String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
