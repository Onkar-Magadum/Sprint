package com.cg.exception;

@SuppressWarnings("serial")
public class HallNotFoundException extends RuntimeException {

    public HallNotFoundException() {
        super("Hall not found");
    }

    public HallNotFoundException(String str) {
        super(str);
    }

}
