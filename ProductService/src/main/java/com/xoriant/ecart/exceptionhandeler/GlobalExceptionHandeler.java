package com.xoriant.ecart.exceptionhandeler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandeler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserInputException.class)
	public ResponseEntity<String> userInputExceptionHandeler(UserInputException exception) {
		return new ResponseEntity<String>("Please check Input fields !", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<String> elementNotFoundException(ElementNotFoundException exception) {
		return new ResponseEntity<String>("Element Not Present in Database !", HttpStatus.BAD_REQUEST);
	}
}
