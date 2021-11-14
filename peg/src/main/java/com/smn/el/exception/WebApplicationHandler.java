package com.smn.el.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RestController
@Slf4j
public class WebApplicationHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ EntityNotFoundException.class, EmptyResultDataAccessException.class })
	public ResponseEntity<Error> handleEntityNotFoundException(RuntimeException ex, WebRequest request) {
		Error errorDetails = new Error(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.NOT_FOUND);
		log.error(ex.getMessage(), ex); 
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<Error> handleValidationException(ValidationException ex, WebRequest request) {
		Error errorDetails = new Error(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.BAD_REQUEST);
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<Error>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		Error errorDetails = new Error(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.BAD_REQUEST);
		log.error(ex.getMessage());
		System.out.println("Hello");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}		
		String errorMessage = details.toString().substring(1, details.toString().length() - 1); 
		Error errorDetails = new Error(new Date(), errorMessage,
				request.getDescription(false), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Error> handleRuntimeException(RuntimeException ex,
			WebRequest request) {
		Error errorDetails = new Error(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.BAD_REQUEST);
		log.error(ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Error> handleException(RuntimeException ex, WebRequest request) {
		Error errorDetails = new Error(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR);
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
