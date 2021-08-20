package com.db.validation;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.db.exception.InvalidMaturityDateException;
import com.db.model.Trade;

@Component
public class MaturityDateValidator implements BaseValidator {

	@Override
	public boolean isValid(Trade trade) throws  InvalidMaturityDateException {
		java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return trade.getMaturityDate().after(currentDate);
	}

}