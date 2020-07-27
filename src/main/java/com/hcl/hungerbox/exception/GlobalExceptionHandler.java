package com.hcl.hungerbox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(ItemNotFoundException itemNotFoundException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND.value(),
				request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(VendorNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(VendorNotFoundException vendorNotFoundException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(vendorNotFoundException.getMessage(), HttpStatus.NOT_FOUND.value(),
				request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(NoDataException noDataException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(noDataException.getMessage(), HttpStatus.NOT_FOUND.value(),
				request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(NullPointerException nullPointerException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(nullPointerException.getMessage(), HttpStatus.NOT_FOUND.value(),
				request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
