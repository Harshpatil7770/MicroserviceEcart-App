package com.xoriant.ecart.exceptionhandeler;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputException extends RuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorMsg;

}
