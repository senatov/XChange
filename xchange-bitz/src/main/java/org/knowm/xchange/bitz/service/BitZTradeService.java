package org.knowm.xchange.bitz.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

import java.io.IOException;

public class BitZTradeService extends BitZTradeServiceRaw implements TradeService {

	public BitZTradeService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
		throw new NotAvailableFromExchangeException();
	}

	@Override
	public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
		throw new NotAvailableFromExchangeException();
	}

	@Override
	public TradeHistoryParams createTradeHistoryParams() {
		throw new NotAvailableFromExchangeException();
	}
}