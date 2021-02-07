package com.ozan.ratesdemo.db.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ozan.ratesdemo.util.Util;

@Entity
@Table(name = "CONVERT_LOG")
public class ConvertLog {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "RATE_DATE", length = 50)
	private String rateDate;
	
	@Column(name = "CURR_FROM", length = 50)
	private String currFrom;
	
	@Column(name = "CURR_TO", length = 50)
	private String currTo;
	
	@Column(name = "VALUE", length = 50)
	private String value;
	
	@Column(name = "ERROR_CODE", length = 10)
	private String errorCode;

	@Column(name = "ERROR", length = 4000)
	private String error;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column
	private Date transactionDate;
	
	public ConvertLog() {
		this.transactionDate = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRateDate() {
		return rateDate;
	}

	public void setRateDate(String str) {
		this.rateDate = Util.cutIfLonger(str, 50);
	}

	public String getCurrFrom() {
		return currFrom;
	}

	public void setCurrFrom(String str) {
		this.currFrom = Util.cutIfLonger(str, 50);
	}

	public String getCurrTo() {
		return currTo;
	}

	public void setCurrTo(String str) {
		this.currTo = Util.cutIfLonger(str, 50);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String str) {
		this.value = Util.cutIfLonger(str, 50);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String str) {
		this.errorCode = Util.cutIfLonger(str, 10);
	}

	public String getError() {
		return error;
	}

	public void setError(String str) {
		this.error = Util.cutIfLonger(str, 4000);
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}