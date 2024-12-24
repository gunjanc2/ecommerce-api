package com.gc2project.ecommerce.exceptions;

public class APIException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//generate no args constructor
	public APIException() {
	}
	
	//generate constructor from superclass with message
	public APIException(String message) {
		super(message);
	}
}
