
import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.exception.InvalidMaturityDateException;
import com.db.exception.InvalidVersionException;
import com.db.model.Trade;
import com.db.service.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { com.db.TradeManagementApplication.class })
public class TradeServiceTest {

	@Autowired
	private TradeService tradeService;

	@Test
	public void testAddTradeValid() throws InvalidMaturityDateException, InvalidVersionException {
		tradeService.createOrUpdateTrade(
				new Trade("T1-TEST", 1, "CP-1", "B1", Date.valueOf("2021-11-30"), Date.valueOf("2021-11-30"), false));
		Assert.assertNotNull(tradeService.getTradeById("T1-TEST"));
	}

	@Test(expected = InvalidMaturityDateException.class)
	public void testAddTradeInvalidMaturityDate() throws InvalidMaturityDateException, InvalidVersionException {
		//MATURITY DATE IN THE PAST
		tradeService.createOrUpdateTrade(
				new Trade("T1-TEST", 1, "CP-1", "B1", Date.valueOf("2020-11-30"), Date.valueOf("2021-11-30"), false));

	}

	@Test(expected = InvalidVersionException.class)
	public void testAddTradeInvalidVersion() throws InvalidMaturityDateException, InvalidVersionException {
		tradeService.createOrUpdateTrade(
				new Trade("T1-TEST", 2, "CP-1", "B1", Date.valueOf("2021-11-30"), Date.valueOf("2021-11-30"), false));
		tradeService.createOrUpdateTrade(
				new Trade("T1-TEST", 3, "CP-1", "B1", Date.valueOf("2021-11-30"), Date.valueOf("2021-11-30"), false));
		//ADD LOWER VERSION
		tradeService.createOrUpdateTrade(
				new Trade("T1-TEST", 1, "CP-1", "B1", Date.valueOf("2021-11-30"), Date.valueOf("2021-11-30"), false));
	}

}
