package org.jsp.event_app.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor

public class NoEventFoundException extends RuntimeException{

	
	private String message;
	
	public NoEventFoundException(String message) {
		this.message=message;
		
	}
	@Override
	public String getMessage() {
		
		return this.message;
	}
}
