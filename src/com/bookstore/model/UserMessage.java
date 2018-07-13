package com.bookstore.model;

public class UserMessage {
	private String message;
	private int errorCode;
	
	public UserMessage(String message) {
		this.message = message;
	}
	
	public UserMessage(int errorCode,String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
