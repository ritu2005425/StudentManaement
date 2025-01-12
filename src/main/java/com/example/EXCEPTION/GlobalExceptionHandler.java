package com.example.EXCEPTION;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AgeNotAllowedException.class)
	public ResponseEntity<String> getException(AgeNotAllowedException e)
	{
		return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(capacityExceededException.class)
	public ResponseEntity<String> getex(capacityExceededException c)
	{
		return new ResponseEntity(c.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
