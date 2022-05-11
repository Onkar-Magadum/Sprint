package com.cg.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(String str) {
		super(str);
	}
}
