package org.knowm.xchange.coinfloor.service;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coinfloor.CoinfloorExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinfloorOrderBookIntegration {

	@Test
	public void fetchOrderBookTest() throws IOException {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CoinfloorExchange.class);
		MarketDataService service = exchange.getMarketDataService();
		OrderBook orderBook = service.getOrderBook(CurrencyPair.BTC_GBP);
		assertThat(orderBook.getBids()).isNotEmpty();
		assertThat(orderBook.getAsks()).isNotEmpty();
		LimitOrder order = orderBook.getBids().get(0);
		assertThat(order.getInstrument()).isEqualTo(CurrencyPair.BTC_GBP);
		assertThat(order.getOriginalAmount()).isGreaterThan(BigDecimal.ZERO);
		assertThat(order.getLimitPrice()).isGreaterThan(BigDecimal.ZERO);
	}
}