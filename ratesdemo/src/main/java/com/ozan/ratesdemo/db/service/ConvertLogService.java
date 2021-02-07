package com.ozan.ratesdemo.db.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ozan.ratesdemo.db.model.ConvertLog;
import com.ozan.ratesdemo.db.repository.ConvertLogRepository;

@Service
public class ConvertLogService {
	@Autowired
	ConvertLogRepository repo;
	
	public List<ConvertLog> getAllByDate(Long id, Date date, Date date2, Pageable pageable) {
		List<ConvertLog> logs = new ArrayList<ConvertLog>();
		repo.findByTransactionDateGreaterThanEqualAndTransactionDateLessThan(date, date2, pageable).forEach(log1 -> logs.add(log1));
		return logs;
	} 

	public List<ConvertLog> getAllById(long id, Pageable pageable) {
		List<ConvertLog> logs = new ArrayList<ConvertLog>();
		repo.findByIdEquals(id, pageable).forEach(log1 -> logs.add(log1));
		return logs;
	}

	public void saveOrUpdate(ConvertLog model) {
		repo.save(model);
	}
}