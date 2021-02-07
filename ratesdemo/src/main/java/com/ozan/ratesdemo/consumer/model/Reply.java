package com.ozan.ratesdemo.consumer.model;

import java.math.BigDecimal;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reply {
	private String base;
	private String date;
	private String error;
	
	@JsonProperty("rates")
	private HashMap<String, BigDecimal> rates;
	
	private Reply() {
		
	}
	
	public String getBase() {
		return base;
	}
	
	public void setBase(String base) {
		this.base = base;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public HashMap<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(HashMap<String, BigDecimal> rates) {
		this.rates = rates;
	}
}