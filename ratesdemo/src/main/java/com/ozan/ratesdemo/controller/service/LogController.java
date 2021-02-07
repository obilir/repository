package com.ozan.ratesdemo.controller.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ozan.ratesdemo.controller.model.LogReply;
import com.ozan.ratesdemo.db.model.ConvertLog;
import com.ozan.ratesdemo.db.service.ConvertLogService;
import com.ozan.ratesdemo.exception.RatesException;
import com.ozan.ratesdemo.exception.RatesExceptionType;
import com.ozan.ratesdemo.util.Util;

@RestController
@JsonInclude(Include.NON_ABSENT)
public class LogController {
	private static final Logger logger = LogManager.getLogger(LogController.class);
	
	@Autowired
	ConvertLogService convertLogService;
	
	@GetMapping("/getLogs")
	public LogReply getLogs(
			@RequestParam(required = false) String page, 
			@RequestParam(required = false) String size,
			@RequestParam(required = false) String transactionId,
			@RequestParam(required = false) String transactionDate
	) {
		logger.info("getLogs(page:"+page+", size:"+size+", transactionId:"+transactionId+", transactionDate:"+transactionDate+")");
		LogReply reply = new LogReply();
		try {
			int parPage = 1;
			if(!Util.isEmpty(page))
				parPage = Util.getIntValueOfString(page);
			
			int parSize = 10;
			if(!Util.isEmpty(size))
				parSize = Util.getIntValueOfString(size);
			
			Date parDate = null;
			Date parDate2 = null;
			if(!Util.isEmpty(transactionDate)) {
				parDate = Util.getDateFromStr(transactionDate, "yyyy-MM-dd");
				parDate2 = new Date(parDate.getTime()+(24 * 60 * 60 * 1000));
				logger.info(parDate);
				logger.info(parDate2);
			}

			Long parTransactionId = null;
			if(!Util.isEmpty(transactionId))
				parTransactionId = Util.getLongValueOfString(transactionId);
			
			if(parPage <= 0)
				throw new RatesException(RatesExceptionType.INVALID_PARAMETER, "page:"+page);

			if(parSize <= 0)
				throw new RatesException(RatesExceptionType.INVALID_PARAMETER, "size:"+size);
			
			if(parDate == null && parTransactionId == null)
				throw new RatesException(RatesExceptionType.INVALID_PARAMETER, "transactionId or transactionDate muste be specified");
			
			Pageable resultPage = PageRequest.of(parPage-1, parSize);
			
			List<ConvertLog> logs = null;
			
			if(parTransactionId != null) 
				logs = convertLogService.getAllById(parTransactionId.longValue(), PageRequest.of(0,1));
			else
				logs = convertLogService.getAllByDate(parTransactionId, parDate, parDate2, resultPage);
			
			reply.setLogs(logs);
		} catch (Exception e) {
			reply.setException(e);
			logger.info(Util.getStackTrace(e));
		}
		return reply;
	}
}