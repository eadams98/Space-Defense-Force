package com.group.sdf.utility;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ControllerAdvice {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Environment env;
	
	@ExceptionHandler
    public ResponseEntity<ErrorInfo> handleAccessDeniedException(ExpiredJwtException ex, HttpServletRequest request) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(ex.getMessage());
		error.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleUsernameNotFoundException(Exception exception) {
		logger.info("handle username not found exception");
		ErrorInfo error = new ErrorInfo();
		logger.info("1");
		error.setErrorMessage(exception.getMessage());
		logger.info("2");
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.info("3");
		error.setTimestamp(LocalDateTime.now());
		logger.info("4");
		ResponseEntity<ErrorInfo> object = new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		logger.info("5");
		return object;
	}
	
	@ExceptionHandler(value = IOException.class)
	public ResponseEntity<ErrorInfo> handleIOException(Exception exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ServletException.class)
	public ResponseEntity<ErrorInfo> handleServletException(Exception exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
