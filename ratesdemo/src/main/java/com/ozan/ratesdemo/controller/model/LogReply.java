package com.ozan.ratesdemo.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.ozan.ratesdemo.db.model.ConvertLog;

public class LogReply extends RateWsReplyWithError {
	public LogReply() {
		logs = new ArrayList<>();
	}
	
	private List<ConvertLog> logs;

	public List<ConvertLog> getLogs() {
		return logs;
	}

	public void setLogs(List<ConvertLog> logs) {
		this.logs = logs;
	}
}