package com.dbs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value = InvalidEmployeeException.class)
	public ResponseEntity<ErrorResponse> meth1(InvalidEmployeeException invalidEmployeeException) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = invalidEmployeeException.getMessage();
		ErrorResponse errorMessage = new ErrorResponse(status, message);
		return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidDepartmentException.class)
	public ResponseEntity<ErrorResponse> meth2(InvalidDepartmentException invalidDepartmentException) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = invalidDepartmentException.getMessage();
		ErrorResponse errorMessage = new ErrorResponse(status, message);
		return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> meth3(Exception exception){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = exception.getMessage();
		ErrorResponse errorMessage = new ErrorResponse(status, message);
		return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
	}

}
