package com.db.exception;

public class InvalidTradeException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidTradeException(String message) {
		super(message);
	}

}
