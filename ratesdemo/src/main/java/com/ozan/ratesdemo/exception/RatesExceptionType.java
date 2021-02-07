package com.ozan.ratesdemo.exception;

public enum RatesExceptionType {
	UNKNOWN_EXCEPTION("0"),
	DATE_FORMAT_EXCEPTION("1"),
	INVALID_NUMERIC_VALUE_EXCEPTION("2"),
	INVALID_PARAMETER("3");
	
	private String errorCode;

	RatesExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
