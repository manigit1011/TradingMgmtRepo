package com.db.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.exception.InvalidMaturityDateException;
import com.db.exception.InvalidVersionException;
import com.db.model.Trade;
import com.db.repository.TradeRepository;
import com.db.validation.MaturityDateValidator;
import com.db.validation.VersionValidator;

@Service
public class TradeService {
	private Logger logger = LoggerFactory.getLogger(TradeService.class);

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private VersionValidator versionValidator;

	@Autowired
	private MaturityDateValidator maturityDateValidator;

	@Autowired
	private ModelMapper modelMapper;

	public List<Trade> getAllTrades() {
		List<Trade> tradeList = tradeRepository.findAll();
		logger.info("Trades retrieved successfully");

		return tradeList;

	}

	public List<Trade> getTradeById(String tradeId) {
		Optional<List<Trade>> tradIdListOpt = tradeRepository.findByTradeId(tradeId);
		if (tradIdListOpt.isPresent()) {
			logger.info("Trade retrieved successfully");
			return tradIdListOpt.get();
		}

		logger.info("No trade present by id : " + tradeId);
		return Collections.EMPTY_LIST;
	}

	public Trade createOrUpdateTrade(Trade tradeEntity) throws InvalidVersionException, InvalidMaturityDateException {
		if (!versionValidator.isValid(tradeEntity)) {
			logger.error("Version number cannot be less than the trade already existing in the store");
			throw new InvalidVersionException(
					"Version number cannot be less than the trade already existing in the store");
		}
		if (!maturityDateValidator.isValid(tradeEntity)) {
			logger.error("Maturity date cannot be less then today's date.");
			throw new InvalidMaturityDateException("Maturity date cannot be less then today's date.");
		}
		Optional<Trade> tradeOpt = tradeRepository.findByTradeIdAndVersion(tradeEntity.getTradeId(),
				tradeEntity.getVersion());
		Trade savedTrade;
		if (tradeOpt.isPresent()) {
			modelMapper.map(tradeEntity, tradeOpt.get());
			savedTrade = tradeRepository.save(tradeOpt.get());
		} else {
			savedTrade = tradeRepository.save(tradeEntity);
		}
		logger.info("Trade saved successfully");
		return savedTrade;
	}
}
