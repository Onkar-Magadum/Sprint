package com.cg.exception;

@SuppressWarnings("serial")
public class CustomerNotLoggedInException extends RuntimeException {

	public CustomerNotLoggedInException() {
	}

	public CustomerNotLoggedInException(String str) {
		super(str);
	}

}
