package com.pack.usermicroservice.exception;

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}
