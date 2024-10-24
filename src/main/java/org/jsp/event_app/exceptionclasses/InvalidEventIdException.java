package org.jsp.event_app.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class InvalidEventIdException extends RuntimeException {

	private String message;
	private InvalidEventIdException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		
		return this.message;
	}
	
}
