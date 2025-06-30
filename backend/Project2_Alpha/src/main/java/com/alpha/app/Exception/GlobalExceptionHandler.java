package com.alpha.app.Exception;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)	
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex)
	{
		Map<String, String> errorMap = new TreeMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex) {
        // Custom error response or logging
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage() +" Duplicate key error occurred. Please provide a valid value.");
    }

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundExcep(ResourceNotFoundException error)
    {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
	
	@ExceptionHandler(UserHandlingException.class)
    public ResponseEntity<String> handleUserRelatedExcep(ResourceNotFoundException error)
    {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
	
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(ConstraintViolationException.class)
//	public Map<String, String> handleValidationArgument(ConstraintViolationException ex)
//	{
//		Map<String, String> errorMap = new TreeMap<String, String>();
//		ex.getBindingResult().getFieldErrors().forEach(error -> {
//			errorMap.put(error.getField(), error.getDefaultMessage());
//		});
//		return errorMap;
//	}
}
