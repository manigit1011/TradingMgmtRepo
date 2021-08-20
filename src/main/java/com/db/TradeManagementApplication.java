package com.db;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.db.repository.TradeRepository;

@SpringBootApplication
@EnableScheduling
public class TradeManagementApplication  {

	private static final Logger log = LoggerFactory.getLogger(TradeManagementApplication.class);
	@Autowired
	private TradeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TradeManagementApplication.class);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	/*
	 * public void run(String... args) throws Exception { java.sql.Date currentDate
	 * = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	 * repository.save(new com.db.model.Trade("T1", 1, "CP-1", "B1", currentDate,
	 * currentDate, false)); repository.save(new Trade("T2", 2, "CP-2", "B1",
	 * Date.valueOf("2021-05-20"), currentDate, false));
	 * 
	 * }
	 */
}