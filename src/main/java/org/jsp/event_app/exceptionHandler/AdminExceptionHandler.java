package org.jsp.event_app.exceptionHandler;

import org.jsp.cms.exceptionClasses.InvalidCredentialsException;
import org.jsp.event_app.exceptionclasses.InvalidAdminIdException;
import org.jsp.event_app.exceptionclasses.NoAdminFoundException;
import org.jsp.event_app.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminExceptionHandler {

	@ExceptionHandler(NoAdminFoundException.class)
	public ResponseEntity<?> NoAdminFoundException(NoAdminFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No Admin Found...").body(e.getMessage()).build());
	}

	@ExceptionHandler(InvalidAdminIdException.class)
	public ResponseEntity<?> InvalidAdminIdException(InvalidAdminIdException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("Invalid Admin Id...").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<?> InvalidCredentialsException(InvalidCredentialsException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("Invalid phone or password...").body(e.getMessage()).build());
	}
	
}
