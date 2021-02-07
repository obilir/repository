package com.ozan.ratesdemo.controller.model;

import com.ozan.ratesdemo.exception.RatesException;
import com.ozan.ratesdemo.exception.RatesExceptionType;

abstract class RateWsReplyWithError {
	private String errorCode;
	private String errorExplanation;
	
	public RateWsReplyWithError() {
		
	}
	
	public void setException(Exception e) {
		if(e instanceof RatesException) {
			this.errorCode = ((RatesException)e).getRatesExceptionType().getErrorCode();
			this.errorExplanation = ((RatesException)e).getErrorMessage();
		} else {
			this.errorCode = RatesExceptionType.UNKNOWN_EXCEPTION.getErrorCode();
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorExplanation() {
		return errorExplanation;
	}

	public void setErrorExplanation(String errorExplanation) {
		this.errorExplanation = errorExplanation;
	}
}