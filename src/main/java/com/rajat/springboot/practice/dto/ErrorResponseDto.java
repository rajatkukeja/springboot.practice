package com.rajat.springboot.practice.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponseDto {

	public HttpStatus statusCode;

	public String statusMsg;

	public String apiPath;

	public LocalDateTime localDateTime;

	public String errorMsg;

	public ErrorResponseDto() {
	}

	public ErrorResponseDto(HttpStatus statusCode, String statusMsg, String apiPath, LocalDateTime localDateTime,
			String errorMsg) {
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
		this.apiPath = apiPath;
		this.localDateTime = localDateTime;
		this.errorMsg = errorMsg;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
