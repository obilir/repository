package com.ozan.ratesdemo.controller.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ozan.ratesdemo.consumer.service.RateConsumerService;
import com.ozan.ratesdemo.controller.model.ConvertReply;
import com.ozan.ratesdemo.controller.model.RateReply;
import com.ozan.ratesdemo.db.model.ConvertLog;
import com.ozan.ratesdemo.db.service.ConvertLogService;
import com.ozan.ratesdemo.util.Util;

@RestController
@JsonInclude(Include.NON_ABSENT)
public class RateController {
	private static final Logger logger = LogManager.getLogger(RateController.class);
	
	@Autowired
	ConvertLogService convertLogService;
	
	private BigDecimal getRateFromWs(String date, String currFrom, String currTo) throws Exception {
		Date dateParsed = null;
		if (date != null) 
			dateParsed = Util.getDateFromStr(date, "yyyy-MM-dd");
		return RateConsumerService.getRateByConsumingRestApi(dateParsed, currFrom, currTo);
	}

	@GetMapping("/getRate")
	public RateReply getRate(@RequestParam(required = false) String date,
			@RequestParam(required = true) String currFrom, 
			@RequestParam(required = true) String currTo) {
		RateReply reply = new RateReply();
		try {
			BigDecimal rate = getRateFromWs(date, currFrom, currTo);
			reply.setRate(rate);
		} catch (Exception e) {
			reply.setException(e);
			logger.info(Util.getStackTrace(e));
		}
		return reply;
	}

	@GetMapping("/convert")
	public ConvertReply convert(@RequestParam(required = false) String date,
			@RequestParam(required = true) String currFrom, 
			@RequestParam(required = true) String currTo,
			@RequestParam(required = true) String value) {
		ConvertReply reply = new ConvertReply();
		ConvertLog log = new ConvertLog();
		log.setCurrFrom(currFrom);
		log.setCurrTo(currTo);
		log.setValue(value);
		log.setRateDate(date);
		try {
			convertLogService.saveOrUpdate(log);
			BigDecimal rate = getRateFromWs(date, currFrom, currTo);
			BigDecimal val = Util.getBigDecimalValueOfString(value);
			reply.setAmount(rate.multiply(val));
			log.setAmount(reply.getAmount());
			convertLogService.saveOrUpdate(log);
		} catch (Exception e) {
			reply.setException(e);
			log.setErrorCode(reply.getErrorCode());
			log.setError(reply.getErrorExplanation());
			logger.info(Util.getStackTrace(e));
			convertLogService.saveOrUpdate(log);
		}
		reply.setTransactionId(log.getId());
		return reply;
	}
}