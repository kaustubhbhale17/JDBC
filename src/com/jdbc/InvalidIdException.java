package com.jdbc;

public class InvalidIdException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidIdException(String str) {
		super(str);
	}
}
