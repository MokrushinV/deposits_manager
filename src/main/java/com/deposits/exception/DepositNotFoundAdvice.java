package com.deposits.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DepositNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler (DepositNotFoundException.class)
	@ResponseStatus (HttpStatus.NOT_FOUND)
	String depositNotFoundHandler (DepositNotFoundException ex) {
		return ex.getMessage();
	}
}
