package com.tshepo.exception.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tshepo.exception.ItemExistException;
import com.tshepo.exception.NotFoundException;
import com.tshepo.exception.response.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {

	private static final String INTERNAL_SERVER_ERROR = "An error occurred while processing the request";

	@ExceptionHandler(Exception.class)
	public ExceptionResponse handleAllException(Exception exception) {
		log.error(exception.getMessage());
		return buildExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ExceptionResponse handleNotFoundException(NotFoundException exception) {
		log.error(exception.getMessage());
		return buildExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(ItemExistException.class)
	public ExceptionResponse handleItemExistException(ItemExistException exception) {
		log.error(exception.getMessage());
		return buildExceptionResponse(HttpStatus.CONFLICT, exception.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = ExceptionResponse.builder().timeStamp(new Date().toString())
				.statusCode(status.value()).status(HttpStatus.BAD_REQUEST).message(ex.getBindingResult().toString())
				.build();
		return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
	}

	private ExceptionResponse buildExceptionResponse(HttpStatus status, String message) {
		return ExceptionResponse.builder().timeStamp(new Date().toString()).statusCode(status.value()).status(status)
				.message(message).build();
	}

}
