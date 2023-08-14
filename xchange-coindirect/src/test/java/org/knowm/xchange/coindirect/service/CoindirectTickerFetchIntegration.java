package org.knowm.xchange.coindirect.service;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coindirect.CoindirectExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import static org.assertj.core.api.Assertions.assertThat;

public class CoindirectTickerFetchIntegration {
	@Test
	public void tickerFetchTest() throws Exception {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CoindirectExchange.class);
		MarketDataService marketDataService = exchange.getMarketDataService();
		Ticker ticker = marketDataService.getTicker(new CurrencyPair("ETH", "BTC"));
		System.out.println(ticker.toString());
		assertThat(ticker).isNotNull();
	}
}