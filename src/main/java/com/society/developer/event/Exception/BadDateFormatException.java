package com.society.developer.event.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadDateFormatException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BadDateFormatException(String dateString) {
		super(dateString);
	}
}
