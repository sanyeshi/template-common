package com.template.common.exception;

public class JsonDeserializationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonDeserializationException() {
		super();
	}

	public JsonDeserializationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JsonDeserializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonDeserializationException(String message) {
		super(message);
	}

	public JsonDeserializationException(Throwable cause) {
		super(cause);
	}

}
