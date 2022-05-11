package com.cg.exception;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException() {
	}

	public InvalidCredentialsException(String str) {
		super(str);
	}
}
