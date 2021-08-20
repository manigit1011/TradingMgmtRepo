package com.db.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.exception.InvalidMaturityDateException;
import com.db.exception.InvalidVersionException;
import com.db.model.Trade;
import com.db.service.TradeService;

@RestController
@RequestMapping("/trade")
public class TradeController {
	@Autowired
	private TradeService service;

	@GetMapping
	public ResponseEntity<List<Trade>> getAllTrades() {
		List<Trade> tradeEntityList = service.getAllTrades();
		return new ResponseEntity<List<Trade>>(tradeEntityList, HttpStatus.OK);
	}

	@GetMapping("/{trade-id}")
	public ResponseEntity<List<Trade>> getTradeByTradeId(@PathVariable("trade-id") String tradeId) {
		List<Trade> tradeEntityList = service.getTradeById(tradeId);
		return new ResponseEntity<List<Trade>>(tradeEntityList, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Trade> createOrUpdateTrade(@Valid @RequestBody Trade TradeEntity) throws InvalidVersionException, InvalidMaturityDateException {
		Trade TradeEntitySaved = service.createOrUpdateTrade(TradeEntity);
		return new ResponseEntity<Trade>(TradeEntitySaved, HttpStatus.OK);
	}

}