package com.ozan.ratesdemo.consumer.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.ozan.ratesdemo.consumer.model.Reply;
import com.ozan.ratesdemo.exception.RatesException;
import com.ozan.ratesdemo.exception.RatesExceptionType;

public class RateConsumerService {
	private static final Logger logger = LogManager.getLogger(RateConsumerService.class);
	
	public static BigDecimal getRateByConsumingRestApi(Date date, String currFrom, String currTo) throws RatesException {
		BigDecimal rate = BigDecimal.ZERO;
		try {
			String dateStr = "latest";
			String url = "https://api.ratesapi.io/api/%s?base=%s&symbols=%s";
			if(date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dateStr = sdf.format(date);
			}
			
			url = String.format(url, dateStr, currFrom, currTo);
			RestTemplate restTemplate = new RestTemplate();
			Reply reply = restTemplate.getForObject(url, Reply.class);
			logger.info("Got:"+url);
			if(reply.getRates().size() > 0) {
				rate = reply.getRates().get(currTo);
				logger.info("rate:"+rate);
			}
		} catch (Exception e) {
			if(e instanceof RatesException) {
				throw e;
			} else {
				logger.error(e);
				throw new RatesException(RatesExceptionType.UNKNOWN_EXCEPTION, e.getMessage());
			}
		}
		return rate;
	}
}