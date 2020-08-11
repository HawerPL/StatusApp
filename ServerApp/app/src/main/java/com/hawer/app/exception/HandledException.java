package com.hawer.app.exception;

public class HandledException extends Exception{

	private static final long serialVersionUID = 2119613345710072394L;
	
	private String code;

    public HandledException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public HandledException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
