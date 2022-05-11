package com.cg.exception;

@SuppressWarnings("serial")
public class AdminNotFoundException extends RuntimeException {

	public AdminNotFoundException() {
	}

	public AdminNotFoundException(String str) {
		super(str);
	}

}
