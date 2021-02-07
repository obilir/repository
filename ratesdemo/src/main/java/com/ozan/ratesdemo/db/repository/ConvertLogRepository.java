package com.ozan.ratesdemo.db.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ozan.ratesdemo.db.model.ConvertLog;

public interface ConvertLogRepository extends JpaRepository<ConvertLog, Long> {
	Page<ConvertLog> findByIdEquals(long id, Pageable pageable);
	Page<ConvertLog> findByTransactionDateGreaterThanEqualAndTransactionDateLessThan(Date id, Date id2, Pageable pageable);
}