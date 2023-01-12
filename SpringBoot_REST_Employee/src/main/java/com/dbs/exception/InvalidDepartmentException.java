package com.dbs.exception;

public class InvalidDepartmentException extends Exception {

	public InvalidDepartmentException() {
		super();
	}

	public InvalidDepartmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDepartmentException(String message) {
		super(message);
	}

	public InvalidDepartmentException(Throwable cause) {
		super(cause);
	}

}
