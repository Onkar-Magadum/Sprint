package com.cg.exception;

@SuppressWarnings("serial")
public class AdminAvailableException extends RuntimeException{

    public AdminAvailableException() {}
	
	public AdminAvailableException(String str) {
		super(str);
	}

}
