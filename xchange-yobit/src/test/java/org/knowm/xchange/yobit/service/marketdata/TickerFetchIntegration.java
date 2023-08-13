package org.knowm.xchange.yobit.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.yobit.YoBitExchange;

import static org.assertj.core.api.Assertions.assertThat;

public class TickerFetchIntegration {

	@Test
	public void tickerFetchTest() throws Exception {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(YoBitExchange.class);
		MarketDataService marketDataService = exchange.getMarketDataService();
		Ticker ticker = marketDataService.getTicker(new CurrencyPair("LTC", "BTC"));
		System.out.println(ticker.toString());
		assertThat(ticker.getInstrument()).isEqualTo(new CurrencyPair("LTC", "BTC"));
		assertThat(ticker).isNotNull();
		Ticker ticker2 = marketDataService.getTicker(new CurrencyPair("ETH", "BTC"));
		assertThat(ticker2).isNotNull();
		assertThat(ticker2.getInstrument()).isEqualTo(new CurrencyPair("ETH", "BTC"));
		assertThat(ticker2.getLast()).isNotEqualTo(ticker.getLast());
	}
}