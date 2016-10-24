package com.kaufland.exercise.Service;

/**
 * @author Pezhman Jahanmard
 */
public class AnagramServiceException extends Exception {
	public AnagramServiceException() {
		super();
	}

	public AnagramServiceException(String message) {
		super(message);
	}

	public AnagramServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnagramServiceException(Throwable cause) {
		super(cause);
	}
}
