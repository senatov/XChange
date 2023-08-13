package org.knowm.xchange.kraken.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TickerFetchIntegration {

	@Test
	public void tickerFetchTest() throws Exception {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class);
		MarketDataService marketDataService = exchange.getMarketDataService();
		Instrument currencyPair = new CurrencyPair("BTC", "USD");
		Ticker ticker = marketDataService.getTicker(currencyPair);
		System.out.println(ticker.toString());
		List<Ticker> tickersList = marketDataService.getTickers(null);
		tickersList.forEach(System.out::println);
		assertThat(ticker).isNotNull();
		tickersList.forEach(ticker1 -> {
			assertThat(ticker1.getInstrument()).isNotNull();
			assertThat(ticker1.getInstrument()).isNotNull();
		});
	}
}