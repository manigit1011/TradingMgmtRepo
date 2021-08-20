package com.db.exception;

public class InvalidVersionException extends InvalidTradeException {

	private static final long serialVersionUID = 1L;

	public InvalidVersionException(String message) {
		super(message);
	}

}
