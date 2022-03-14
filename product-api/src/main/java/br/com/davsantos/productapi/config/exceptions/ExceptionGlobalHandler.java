package br.com.davsantos.productapi.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException exception) {
		var detail = new ExceptionDetail();
		detail.setStatus(HttpStatus.BAD_REQUEST.value());
		detail.setMessage(exception.getMessage());

		return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
	}
}
