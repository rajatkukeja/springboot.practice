package com.rajat.springboot.practice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.rajat.springboot.practice.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(exception = RuntimeException.class)
	public ResponseEntity<ErrorResponseDto> runTimeExceptionHandler(Exception exception, WebRequest request) {

		ErrorResponseDto error = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, "Please check the input",
				request.getDescription(false), LocalDateTime.now(), exception.getMessage());
		return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(exception = NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponseDto> notFoundException(NoHandlerFoundException exception, WebRequest request) {

		ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND, "Invalid request. Please check",
				request.getDescription(false), LocalDateTime.now(), exception.getMessage());
		return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.NOT_FOUND);
	}

}
