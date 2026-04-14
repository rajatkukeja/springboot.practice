package com.rajat.springboot.practice.dto;

public class LoginResponseDto {

	private String message;
	private UserDto userDto;
	private String jwtToken;

	public LoginResponseDto() {
	}

	public LoginResponseDto(String message, UserDto userDto, String jwtToken) {
		this.message = message;
		this.userDto = userDto;
		this.jwtToken = jwtToken;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
