package com.dbs.exception;

public class InvalidEmployeeException extends Exception {

	public InvalidEmployeeException() {
		super();
	}

	public InvalidEmployeeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEmployeeException(String message) {
		super(message);
	}

	public InvalidEmployeeException(Throwable cause) {
		super(cause);
	}

	
}
