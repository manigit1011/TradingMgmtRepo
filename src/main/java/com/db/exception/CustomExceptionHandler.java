package com.db.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundError(NotFoundException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		error.setCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidTradeException.class)
	protected ResponseEntity<Object> handleBadRequest(InvalidTradeException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setDate(LocalDateTime.now());
		error.setNextAction("Invalid trade details. Please check the input.");
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}