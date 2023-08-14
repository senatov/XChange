package org.knowm.xchange.gateio.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.gateio.GateioExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author timmolter
 */
public class TickerFetchIntegration {

	@Test
	public void tickerFetchTest() throws Exception {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(GateioExchange.class);
		MarketDataService marketDataService = exchange.getMarketDataService();
		Ticker ticker = marketDataService.getTicker(new CurrencyPair("BTC", "USDT"));
		System.out.println(ticker.toString());
		assertThat(ticker).isNotNull();
	}
}