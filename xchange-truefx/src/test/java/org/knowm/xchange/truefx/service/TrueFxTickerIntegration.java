package org.knowm.xchange.truefx.service;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.truefx.TrueFxExchange;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TrueFxTickerIntegration {

	@Test
	public void fetchTickerTest() throws IOException {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(TrueFxExchange.class);
		MarketDataService service = exchange.getMarketDataService();
		Ticker ticker = service.getTicker(CurrencyPair.GBP_USD);
		assertThat(ticker.getInstrument()).isEqualTo(CurrencyPair.GBP_USD);
		assertThat(ticker.getBid()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getHigh()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getAsk()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getLow()).isGreaterThan(BigDecimal.ZERO);
		assertThat(ticker.getLast()).isNull();
		assertThat(ticker.getVwap()).isNull();
		assertThat(ticker.getVolume()).isNull();
	}
}