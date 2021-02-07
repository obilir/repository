package com.ozan.ratesdemo.exception;

public class RatesException extends Exception {
	private static final long serialVersionUID = -8752056575381537558L;
	
	private String errorMessage;
	private RatesExceptionType ratesExceptionType;
	
	public RatesException(RatesExceptionType ratesExceptionType, String errorMessage) {
		this.errorMessage = errorMessage;
		this.ratesExceptionType = ratesExceptionType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public RatesExceptionType getRatesExceptionType() {
		return ratesExceptionType;
	}
}