package com.tshepo.exception.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class ExceptionResponse {
	
	protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;

}
