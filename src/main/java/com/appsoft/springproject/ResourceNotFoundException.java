package com.appsoft.springproject;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException() {
		 super();
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
