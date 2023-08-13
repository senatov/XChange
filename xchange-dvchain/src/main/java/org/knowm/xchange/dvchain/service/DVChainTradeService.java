package org.knowm.xchange.dvchain.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.dvchain.DVChainAdapters;
import org.knowm.xchange.dvchain.dto.marketdata.DVChainLevel;
import org.knowm.xchange.dvchain.dto.marketdata.DVChainMarketData;
import org.knowm.xchange.dvchain.dto.marketdata.DVChainMarketResponse;
import org.knowm.xchange.dvchain.dto.trade.DVChainNewLimitOrder;
import org.knowm.xchange.dvchain.dto.trade.DVChainNewMarketOrder;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class DVChainTradeService extends DVChainTradeServiceRaw implements TradeService {
	private DVChainMarketDataService marketDataService;

	public DVChainTradeService(DVChainMarketDataService marketDataService, Exchange exchange) {
		super(exchange);
		this.marketDataService = marketDataService;
	}

	@Override
	public OpenOrders getOpenOrders() throws IOException {
		return DVChainAdapters.adaptOpenOrders(getOrders());
	}

	@Override
	public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
		DVChainMarketResponse marketResponse = marketDataService.getMarketData();
		DVChainMarketData marketData =
				marketResponse.getMarketData().get(marketOrder.getInstrument().getBase().getSymbol());
		List<DVChainLevel> levels = marketData.getLevels();
		String side = marketOrder.getType() == Order.OrderType.BID ? "Buy" : "Sell";
		DVChainNewMarketOrder dvChainNewMarketOrder =
				new DVChainNewMarketOrder(
						side,
						getPriceForMarketOrder(levels, marketOrder),
						marketOrder.getOriginalAmount(),
						marketOrder.getInstrument().getBase().getSymbol());
		return newDVChainMarketOrder(dvChainNewMarketOrder).toString();
	}

	private BigDecimal getPriceForMarketOrder(List<DVChainLevel> levels, MarketOrder marketOrder) {
		BigDecimal quantity = marketOrder.getOriginalAmount();
		for (DVChainLevel level : levels) {
			if (quantity.compareTo(level.getMaxQuantity()) <= 0) {
				return marketOrder.getType() == Order.OrderType.BID
						? level.getBuyPrice()
						: level.getSellPrice();
			}
		}
		throw new FundsExceededException();
	}

	@Override
	public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
		String side = limitOrder.getType() == Order.OrderType.BID ? "Buy" : "Sell";
		DVChainNewLimitOrder dvChainNewLimitOrder =
				new DVChainNewLimitOrder(
						side,
						limitOrder.getLimitPrice(),
						limitOrder.getOriginalAmount(),
						limitOrder.getInstrument().getBase().getSymbol());
		return newDVChainLimitOrder(dvChainNewLimitOrder).toString();
	}

	@Override
	public boolean cancelOrder(String orderId) throws IOException {
		return cancelDVChainOrder(orderId).equals("");
	}

	@Override
	public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
		return DVChainAdapters.adaptTradeHistory(getTrades());
	}
}