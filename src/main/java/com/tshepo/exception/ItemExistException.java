package com.tshepo.exception;

@SuppressWarnings("serial")
public class ItemExistException extends RuntimeException{
	
	public ItemExistException(String message) {
		super(message);
	}

}
