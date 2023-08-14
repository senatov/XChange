package org.knowm.xchange.huobi.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.IOrderFlags;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.StopOrder;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.huobi.HuobiAdapters;
import org.knowm.xchange.huobi.dto.trade.HuobiOrder;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderByIdParams;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.CurrencyPairParam;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParams;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public class HuobiTradeService extends HuobiTradeServiceRaw implements TradeService {
	public static IOrderFlags FOK = new IOrderFlags() {
	};
	public static IOrderFlags IOC = new IOrderFlags() {
	};
	public static IOrderFlags POST_ONLY = new IOrderFlags() {
	};

	public HuobiTradeService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public OpenOrders getOpenOrders() throws IOException {
		return getOpenOrders(createOpenOrdersParams());
	}

	@Override
	public OpenOrders getOpenOrders(OpenOrdersParams openOrdersParams) throws IOException {
		if (!(openOrdersParams instanceof CurrencyPairParam))
			throw new IllegalArgumentException();
		HuobiOrder[] openOrders = getHuobiOpenOrders((CurrencyPairParam) openOrdersParams);
		return HuobiAdapters.adaptOpenOrders(openOrders);
	}

	@Override
	public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
		return placeHuobiMarketOrder(marketOrder);
	}

	@Override
	public String placeStopOrder(StopOrder stopOrder) throws IOException {
		return null;
	}

	@Override
	public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
		return placeHuobiLimitOrder(limitOrder);
	}

	@Override
	public boolean cancelOrder(String orderId) throws IOException {
		return cancelHuobiOrder(orderId).length() > 0;
	}

	@Override
	public boolean cancelOrder(CancelOrderParams cancelOrderParams) throws IOException {
		return cancelOrderParams instanceof CancelOrderByIdParams
				&& cancelOrder(((CancelOrderByIdParams) cancelOrderParams).getOrderId());
	}

	/**
	 * Huobi trade history only goes back 48h - a new API was promised in 2019-Q1
	 */
	@Override
	public UserTrades getTradeHistory(TradeHistoryParams tradeHistoryParams) throws IOException {
		CurrencyPair currencyPair = null;
		if (tradeHistoryParams instanceof TradeHistoryParamCurrencyPair) {
			currencyPair = ((TradeHistoryParamCurrencyPair) tradeHistoryParams).getCurrencyPair();
		}
		if (currencyPair == null) {
			throw new IllegalArgumentException("Currency pair is required.");
		}
		Date startDate;
		Date endDate;
		if (tradeHistoryParams instanceof TradeHistoryParamsTimeSpan) {
			startDate = ((TradeHistoryParamsTimeSpan) tradeHistoryParams).getStartTime();
			endDate = ((TradeHistoryParamsTimeSpan) tradeHistoryParams).getEndTime();
		} else {
			startDate = null;
			endDate = null;
		}
		String startId;
		if (tradeHistoryParams instanceof TradeHistoryParamsIdSpan) {
			startId = ((TradeHistoryParamsIdSpan) tradeHistoryParams).getStartId();
		} else {
			startId = null;
		}
		HuobiOrder[] tradeHistory = getHuobiTradeHistory(currencyPair, startDate, endDate, startId);
		return HuobiAdapters.adaptTradeHistory(tradeHistory);
	}

	@Override
	public TradeHistoryParams createTradeHistoryParams() {
		return new HuobiTradeHistoryParams();
	}

	@Override
	public OpenOrdersParams createOpenOrdersParams() {
		return new DefaultOpenOrdersParamCurrencyPair();
	}

	@Override
	public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
		return HuobiAdapters.adaptOrders(getHuobiOrder(TradeService.toOrderIds(orderQueryParams)));
	}
}