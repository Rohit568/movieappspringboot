package com.pack.authmicroservice.exception;


public class UsernameOrPasswordInvalidException extends RuntimeException{
	
	public UsernameOrPasswordInvalidException(String msg) {
		super(msg);
	}

}
