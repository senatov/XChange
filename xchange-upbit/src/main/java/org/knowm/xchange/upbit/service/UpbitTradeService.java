package org.knowm.xchange.upbit.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.DefaultCancelOrderParamId;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParams;
import org.knowm.xchange.upbit.UpbitAdapters;
import org.knowm.xchange.upbit.dto.trade.UpbitOrderResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class UpbitTradeService extends UpbitTradeServiceRaw implements TradeService {

	/**
	 *
	 */
	public UpbitTradeService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
		return super.limitOrder(limitOrder).getUuid();
	}

	@Override
	public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {
		if (orderParams instanceof DefaultCancelOrderParamId) {
			final String orderId = ((DefaultCancelOrderParamId) orderParams).getOrderId();
			return cancelOrderRaw(orderId) != null;
		} else {
			return false;
		}
	}

	@Override
	public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
		ArrayList<Order> rtn = new ArrayList<>();
		for (OrderQueryParams params : orderQueryParams) {
			UpbitOrderResponse res = getOrderRaw(params.getOrderId());
			rtn.add(UpbitAdapters.adaptOrderInfo(res));
		}
		return rtn;
	}
}