package com.db.job;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.model.Trade;
import com.db.repository.TradeRepository;
import com.db.service.TradeService;

@Component
public class MaturityDateTrackerJob {
	private Logger logger = LoggerFactory.getLogger(MaturityDateTrackerJob.class);

	@Autowired
	private TradeRepository tradeRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void onStart() {
		// RUN AT STARTUP ONCE AND THEN AS PER SCHEDULE
		logger.info("Running the Maturity Date Tracker Job at startup.");

		scheduleTaskUsingCronExpression();
	}

	@Scheduled(cron = "${cron.expression}", zone = "Asia/Kolkata")
	public void scheduleTaskUsingCronExpression() {
		List<Trade> allTradesInThePast = tradeRepository
				.findAllByMaturityDateLessThanAndExpired(Date.valueOf(LocalDate.now()), false);
		if (!allTradesInThePast.isEmpty()) {
			logger.info("Running the Maturity Date Tracker Job - Number of trades to mark as expired: " + allTradesInThePast.size());
			allTradesInThePast.forEach(trade -> trade.setExpired(true));
			tradeRepository.saveAll(allTradesInThePast);
		} else {
			logger.info("Running the Maturity Date Tracker Job - No trades to be marked as expired");
		}

	}
}
