package com.db.exception;

public class InvalidMaturityDateException extends InvalidTradeException {

	private static final long serialVersionUID = 1L;

	public InvalidMaturityDateException(String message) {
		super(message);
	}

}
