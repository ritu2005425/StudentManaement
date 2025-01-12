package com.example.EXCEPTION;

public class AgeNotAllowedException extends RuntimeException {
	public AgeNotAllowedException(String s)
	{
		super(s);
	}

}
