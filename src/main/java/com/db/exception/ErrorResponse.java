package com.db.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

	private LocalDateTime date;
	private String message;
	private int code;
	private String nextAction;

	public void setTimestamp(LocalDateTime date) {
		this.date = date;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public String getNextAction() {
		return nextAction;
	}

}
