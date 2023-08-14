package org.knowm.xchange.kraken.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.kraken.KrakenAdapters;
import org.knowm.xchange.kraken.dto.marketdata.KrakenDepth;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicTrades;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

import java.io.IOException;
import java.util.List;

public class KrakenMarketDataService extends KrakenMarketDataServiceRaw
		implements MarketDataService {

	/**
	 * Constructor
	 */
	public KrakenMarketDataService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
		return KrakenAdapters.adaptTicker(getKrakenTicker(currencyPair), currencyPair);
	}

	@Override
	public List<Ticker> getTickers(Params params) throws IOException {
		return KrakenAdapters.adaptTickers(getKrakenTickers());
	}

	@Override
	public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
		long count = Long.MAX_VALUE;
		if (args != null && args.length > 0) {
			Object arg0 = args[0];
			if (arg0 instanceof Long) {
				count = (Long) arg0;
			} else if (arg0 instanceof Integer) {
				count = (Integer) arg0;
			} else {
				throw new ExchangeException("args[0] must be of type Long or Integer");
			}
		}
		KrakenDepth krakenDepth = getKrakenDepth(currencyPair, count);
		return KrakenAdapters.adaptOrderBook(krakenDepth, currencyPair);
	}

	@Override
	public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
		Long since = null;
		if (args != null && args.length > 0) {
			Object arg0 = args[0];
			if (arg0 instanceof Long) {
				since = (Long) arg0;
			} else {
				throw new ExchangeException("args[0] must be of type Long!");
			}
		}
		KrakenPublicTrades krakenTrades = getKrakenTrades(currencyPair, since);
		return KrakenAdapters.adaptTrades(
				krakenTrades.getTrades(), currencyPair, krakenTrades.getLast());
	}
}