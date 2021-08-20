package com.db.validation;

import com.db.exception.InvalidTradeException;
import com.db.model.Trade;

public interface BaseValidator {
	boolean isValid(Trade t) throws InvalidTradeException;

}
