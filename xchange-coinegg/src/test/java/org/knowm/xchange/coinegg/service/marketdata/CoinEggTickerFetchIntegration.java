package org.knowm.xchange.coinegg.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coinegg.CoinEggExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinEggTickerFetchIntegration {

	@Test
	public void tickerFetchTest() throws Exception {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CoinEggExchange.class);
		MarketDataService marketDataService = exchange.getMarketDataService();
		Ticker ticker = marketDataService.getTicker(CurrencyPair.ETH_BTC);
		// Verify Not Null Values
		assertThat(ticker).isNotNull();
		assertThat(ticker.getLast()).isNotNull();
		assertThat(ticker.getAsk()).isNotNull();
		assertThat(ticker.getBid()).isNotNull();
		assertThat(ticker.getHigh()).isNotNull();
		assertThat(ticker.getLow()).isNotNull();
		assertThat(ticker.getVolume()).isNotNull();
	}
}