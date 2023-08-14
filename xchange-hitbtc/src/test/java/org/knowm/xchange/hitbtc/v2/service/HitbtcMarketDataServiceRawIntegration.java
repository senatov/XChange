package org.knowm.xchange.hitbtc.v2.service;

import org.junit.Test;
import org.knowm.xchange.hitbtc.v2.BaseServiceTest;
import org.knowm.xchange.hitbtc.v2.dto.HitbtcCurrency;
import org.knowm.xchange.hitbtc.v2.dto.HitbtcSymbol;
import org.knowm.xchange.hitbtc.v2.dto.HitbtcTicker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class HitbtcMarketDataServiceRawIntegration extends BaseServiceTest {

	private final MarketDataService marketDataService = exchange().getMarketDataService();
	private final HitbtcMarketDataServiceRaw marketDataServiceRaw =
			(HitbtcMarketDataServiceRaw) marketDataService;

	@Test
	public void testGetHitbtcSymbols() throws IOException {
		List<HitbtcSymbol> symbols = marketDataServiceRaw.getHitbtcSymbols();
		assertNotNull(symbols);
		assertFalse(symbols.isEmpty());
	}

	@Test
	public void testGetHitbtcCurrencies() throws IOException {
		List<HitbtcCurrency> currencies = marketDataServiceRaw.getHitbtcCurrencies();
		assertNotNull(currencies);
		assertFalse(currencies.isEmpty());
		HitbtcCurrency currency = marketDataServiceRaw.getHitbtcCurrency("btc");
		assertNotNull(currency);
		assertEquals("BTC", currency.getId());
	}

	@Test
	public void testGetHitbtcTickers() throws IOException {
		Map<String, HitbtcTicker> tickers = marketDataServiceRaw.getHitbtcTickers();
		assertThat(tickers).isNotEmpty();
		assertThat(tickers.get("BTCUSD")).isNotNull();
	}
}