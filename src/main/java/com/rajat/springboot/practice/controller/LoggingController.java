package com.rajat.springboot.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logging")
public class LoggingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@GetMapping(path = "/public")
	public ResponseEntity<String> getLogging() {
		LOGGER.trace("Trace: This is a very detailed trace log. Used for tracking execution flow.");
		LOGGER.debug("DEBUG: This is a debug message. Used for debugging");
		LOGGER.info("This is an information message. Application events");
		LOGGER.info("WARN: This is a warning! omething might go wrong.");
		LOGGER.info("ERROR: An error occured. This needs immediate action");
		return ResponseEntity.status(HttpStatus.OK).body("Logging tested successfully!");
	}

}
