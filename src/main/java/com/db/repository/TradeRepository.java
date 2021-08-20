package com.db.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {

	Optional<List<Trade>> findByTradeId(String tradeId);

	Optional<Trade>  findByTradeIdAndVersion(String tradeId, Integer version);

	List<Trade> findAllByMaturityDateLessThan(Date date);

	List<Trade> findAllByMaturityDateLessThanAndExpired(Date valueOf, Boolean expired);
}