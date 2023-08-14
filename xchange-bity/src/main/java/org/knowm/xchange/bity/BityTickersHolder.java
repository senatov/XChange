package org.knowm.xchange.bity;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BityTickersHolder {

	private final Map<CurrencyPair, Ticker> mapTickers = new HashMap<>();

	public BityTickersHolder(List<Ticker> tickers) {
		for (Ticker ticker : tickers) {
			mapTickers.put(ticker.getCurrencyPair(), ticker);
		}
	}

	public Ticker getTicker(CurrencyPair currencyPair) {
		return mapTickers.get(currencyPair);
	}
}