package org.knowm.xchange.examples.cexio.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.cexio.CexIOExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;

/**
 * Author: brox Since: 2/6/14
 */
public class TickerDemo {

	public static void main(String[] args) throws IOException {
		// Use the factory to get Cex.IO exchange API using default settings
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CexIOExchange.class);
		// Interested in the public market data feed (no authentication)
		MarketDataService marketDataService = exchange.getMarketDataService();
		// Get the latest ticker data showing BTC to USD
		Ticker ticker = marketDataService.getTicker(new CurrencyPair(Currency.BTC, Currency.USD));
		System.out.println("Pair: " + ticker.getCurrencyPair());
		System.out.println("Last: " + ticker.getLast());
		System.out.println("Volume: " + ticker.getVolume());
		System.out.println("High: " + ticker.getHigh());
		System.out.println("Low: " + ticker.getLow());
		System.out.println("Bid: " + ticker.getBid());
		System.out.println("Ask: " + ticker.getAsk());
		System.out.println("Timestamp: " + ticker.getTimestamp());
	}
}