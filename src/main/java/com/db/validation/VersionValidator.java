package com.db.validation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.exception.InvalidVersionException;
import com.db.model.Trade;
import com.db.repository.TradeRepository;

@Component
public class VersionValidator implements BaseValidator {
	@Autowired
	private TradeRepository tradeRepository;

	public boolean isValid(Trade trade) throws  InvalidVersionException{
		Optional<List<Trade>> tradesInStoreOpt = tradeRepository.findByTradeId(trade.getTradeId());
		if (tradesInStoreOpt.isPresent()) {
			List<Trade> tradesInStore = tradesInStoreOpt.get();
			tradesInStore.sort(Comparator.comparing(Trade::getVersion));
			if (trade.getVersion() < tradesInStore.get(tradesInStore.size() - 1).getVersion()) {
				return false;
			}
		}
		return true;
	}

}