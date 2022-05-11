package com.cg.exception;

@SuppressWarnings("serial")
public class AdminLoggedInException extends RuntimeException {

	public AdminLoggedInException() {
	}

	public AdminLoggedInException(String str) {
		super(str);
	}

}
