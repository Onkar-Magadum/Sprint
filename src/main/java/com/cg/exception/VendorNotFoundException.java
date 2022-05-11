package com.cg.exception;

@SuppressWarnings("serial")
public class VendorNotFoundException extends RuntimeException {

	public VendorNotFoundException() {
	}

	public VendorNotFoundException(String str) {
		super(str);
	}

}
