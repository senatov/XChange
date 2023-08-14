package org.knowm.xchange.ccex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ccex.CCEXAdapters;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;

/**
 * @author Andraž Prinčič
 */
public class CCEXMarketDataService extends CCEXMarketDataServiceRaw implements MarketDataService {

	public CCEXMarketDataService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
		return CCEXAdapters.adaptTicker(getTicker(currencyPair), currencyPair);
	}

	@Override
	public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
		int depth = 50;
		if (args != null && args.length > 0) {
			if (args[0] instanceof Number arg) {
				depth = arg.intValue();
			}
		}
		return CCEXAdapters.adaptOrderBook(getCCEXOrderBook(currencyPair, depth), currencyPair);
	}

	@Override
	public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
		return CCEXAdapters.adaptTrades(getCCEXTrades(currencyPair), currencyPair);
	}
}