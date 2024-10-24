package org.jsp.event_app.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor

public class NoAdminFoundException extends RuntimeException {

	private String message;
	
	public NoAdminFoundException(String message) {
		this.message=message;
	}
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
