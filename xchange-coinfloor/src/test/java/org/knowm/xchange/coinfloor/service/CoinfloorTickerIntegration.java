package org.knowm.xchange.coinfloor.service;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coinfloor.CoinfloorExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinfloorTickerIntegration {

	@Test
	public void fetchTickerTest() throws IOException {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CoinfloorExchange.class);
		MarketDataService service = exchange.getMarketDataService();
		Ticker ticker = service.getTicker(CurrencyPair.BTC_GBP);
		assertThat(ticker.getInstrument()).isEqualTo(CurrencyPair.BTC_GBP);
		assertThat(ticker.getLast()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getHigh()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getLow()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getVwap()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getVolume()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getBid()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getAsk()).isGreaterThan(BigDecimal.ZERO);
	}
}