package info.bitrich.xchangestream.binance;

import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import org.junit.Test;
import org.knowm.xchange.ExchangeSpecification;

import static info.bitrich.xchangestream.binance.BinanceStreamingExchange.USE_HIGHER_UPDATE_FREQUENCY;
import static info.bitrich.xchangestream.binance.BinanceStreamingExchange.USE_REALTIME_BOOK_TICKER;
import static org.junit.Assert.assertEquals;
import static org.knowm.xchange.currency.CurrencyPair.BTC_USD;
import static org.knowm.xchange.currency.CurrencyPair.DASH_BTC;
import static org.knowm.xchange.currency.CurrencyPair.ETH_BTC;

public class BinanceIntegration {

	@Test
	public void channelCreateUrlTest() {
		BinanceStreamingExchange exchange = (BinanceStreamingExchange)
				StreamingExchangeFactory.INSTANCE.createExchange(BinanceStreamingExchange.class);
		ProductSubscription.ProductSubscriptionBuilder builder = ProductSubscription.create();
		builder.addTicker(BTC_USD).addTicker(DASH_BTC);
		String buildSubscriptionStreams = exchange.buildSubscriptionStreams(builder.build());
		assertEquals("btcusd@ticker/dashbtc@ticker", buildSubscriptionStreams);
		ProductSubscription.ProductSubscriptionBuilder builder2 = ProductSubscription.create();
		builder2
				.addTicker(BTC_USD)
				.addTicker(DASH_BTC)
				.addOrderbook(ETH_BTC);
		String buildSubscriptionStreams2 = exchange.buildSubscriptionStreams(builder2.build());
		assertEquals("btcusd@ticker/dashbtc@ticker/ethbtc@depth", buildSubscriptionStreams2);
	}

	@Test
	public void channelCreateUrlWithUpdateFrequencyTest() {
		ProductSubscription.ProductSubscriptionBuilder builder = ProductSubscription.create();
		builder
				.addTicker(BTC_USD)
				.addTicker(DASH_BTC)
				.addOrderbook(ETH_BTC);
		ExchangeSpecification spec =
				StreamingExchangeFactory.INSTANCE
						.createExchange(BinanceStreamingExchange.class)
						.getDefaultExchangeSpecification();
		spec.setExchangeSpecificParametersItem(USE_HIGHER_UPDATE_FREQUENCY, true);
		BinanceStreamingExchange exchange =
				(BinanceStreamingExchange) StreamingExchangeFactory.INSTANCE.createExchange(spec);
		String buildSubscriptionStreams = exchange.buildSubscriptionStreams(builder.build());
		assertEquals("btcusd@ticker/dashbtc@ticker/ethbtc@depth@100ms", buildSubscriptionStreams);
	}

	@Test
	public void channelCreateUrlWithRealtimeBookTickerTest() {
		ProductSubscription.ProductSubscriptionBuilder builder = ProductSubscription.create();
		builder
				.addTicker(BTC_USD)
				.addTicker(DASH_BTC)
				.addOrderbook(ETH_BTC);
		ExchangeSpecification spec =
				StreamingExchangeFactory.INSTANCE
						.createExchange(BinanceStreamingExchange.class)
						.getDefaultExchangeSpecification();
		spec.setExchangeSpecificParametersItem(USE_REALTIME_BOOK_TICKER, true);
		BinanceStreamingExchange exchange =
				(BinanceStreamingExchange) StreamingExchangeFactory.INSTANCE.createExchange(spec);
		String buildSubscriptionStreams = exchange.buildSubscriptionStreams(builder.build());
		assertEquals(
				"btcusd@bookTicker/dashbtc@bookTicker/ethbtc@depth", buildSubscriptionStreams);
	}
}