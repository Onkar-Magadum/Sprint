package com.cg.exception;

@SuppressWarnings("serial")
public class HallNotAvailableException extends RuntimeException {

	public HallNotAvailableException() {
	}

	public HallNotAvailableException(String str) {
		super(str);
	}

}
