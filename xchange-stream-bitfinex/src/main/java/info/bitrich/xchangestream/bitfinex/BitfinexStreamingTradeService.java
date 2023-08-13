package info.bitrich.xchangestream.bitfinex;

import info.bitrich.xchangestream.bitfinex.dto.BitfinexWebSocketAuthOrder;
import info.bitrich.xchangestream.bitfinex.dto.BitfinexWebSocketAuthPreTrade;
import info.bitrich.xchangestream.bitfinex.dto.BitfinexWebSocketAuthTrade;
import info.bitrich.xchangestream.core.StreamingTradeService;
import io.reactivex.Observable;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.exceptions.ExchangeSecurityException;

import java.util.function.Function;

public class BitfinexStreamingTradeService implements StreamingTradeService {

	private final BitfinexStreamingService service;

	public BitfinexStreamingTradeService(BitfinexStreamingService service) {
		this.service = service;
	}

	@Override
	public Observable<Order> getOrderChanges(CurrencyPair currencyPair, Object... args) {
		return getOrderChanges().filter(o -> currencyPair.equals(o.getCurrencyPair()));
	}

	public Observable<Order> getOrderChanges() {
		return getRawAuthenticatedOrders()
				.filter(o -> o.getId() != 0)
				.map(BitfinexStreamingAdapters::adaptOrder)
				.doOnNext(
						o -> {
							service.scheduleCalculatedBalanceFetch(o.getInstrument().getBase().getCurrencyCode());
							service.scheduleCalculatedBalanceFetch(o.getInstrument().getCounter().getCurrencyCode());
						});
	}

	public Observable<BitfinexWebSocketAuthOrder> getRawAuthenticatedOrders() {
		return withAuthenticatedService(BitfinexStreamingService::getAuthenticatedOrders);
	}

	private <T> Observable<T> withAuthenticatedService(
			Function<BitfinexStreamingService, Observable<T>> serviceConsumer) {
		if (!service.isAuthenticated()) {
			throw new ExchangeSecurityException("Not authenticated");
		}
		return serviceConsumer.apply(service);
	}

	@Override
	public Observable<UserTrade> getUserTrades(CurrencyPair currencyPair, Object... args) {
		return getUserTrades().filter(t -> currencyPair.equals(t.getCurrencyPair()));
	}

	/**
	 * Gets a stream of all user trades to which we are subscribed.
	 *
	 * @return The stream of user trades.
	 */
	public Observable<UserTrade> getUserTrades() {
		return getRawAuthenticatedTrades()
				.filter(o -> o.getId() != 0)
				.map(BitfinexStreamingAdapters::adaptUserTrade)
				.doOnNext(
						t -> {
							service.scheduleCalculatedBalanceFetch(t.getInstrument().getBase().getCurrencyCode());
							service.scheduleCalculatedBalanceFetch(t.getInstrument().getCounter().getCurrencyCode());
						});
	}

	public Observable<BitfinexWebSocketAuthTrade> getRawAuthenticatedTrades() {
		return withAuthenticatedService(BitfinexStreamingService::getAuthenticatedTrades);
	}

	public Observable<BitfinexWebSocketAuthPreTrade> getRawAuthenticatedPreTrades() {
		return withAuthenticatedService(BitfinexStreamingService::getAuthenticatedPreTrades);
	}
}