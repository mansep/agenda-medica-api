package com.mansep.agenda.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable ex) {
		super(ex);
	}

	public BadRequestException(String message, Throwable ex) {
		super(message, ex);
	}

}
