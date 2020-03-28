package com.mansep.agenda.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable ex) {
		super(ex);
	}

	public NotFoundException(String message, Throwable ex) {
		super(message, ex);
	}

}
