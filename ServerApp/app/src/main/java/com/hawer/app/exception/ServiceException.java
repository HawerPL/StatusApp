package com.hawer.app.exception;

public class ServiceException extends Exception{

	static final long serialVersionUID = 7444947834200728738L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(final String message) {
		super(message);
	}
	
}
