package com.cg.exception;

@SuppressWarnings("serial")
public class AdminLoggedOutException extends RuntimeException {

	public AdminLoggedOutException() {
	}

	public AdminLoggedOutException(String str) {
		super(str);
	}

}
