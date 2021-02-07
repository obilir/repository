package com.ozan.ratesdemo.controller.model;

import java.math.BigDecimal;

public class RateReply extends RateWsReplyWithError {
	private BigDecimal rate;
	
	public RateReply() {
		
	}
	
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}