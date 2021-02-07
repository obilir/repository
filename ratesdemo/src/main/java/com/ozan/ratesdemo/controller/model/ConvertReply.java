package com.ozan.ratesdemo.controller.model;

import java.math.BigDecimal;

public class ConvertReply extends RateWsReplyWithError {
	private BigDecimal amount;
	private Long transactionId;
	
	public ConvertReply() {
		
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	
}