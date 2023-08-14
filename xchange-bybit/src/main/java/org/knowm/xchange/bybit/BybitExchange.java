package org.knowm.xchange.bybit;


import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bybit.dto.marketdata.BybitSymbol;
import org.knowm.xchange.bybit.mappers.MarketDataMapper;
import org.knowm.xchange.bybit.service.BybitAccountService;
import org.knowm.xchange.bybit.service.BybitMarketDataService;
import org.knowm.xchange.bybit.service.BybitMarketDataServiceRaw;
import org.knowm.xchange.bybit.service.BybitTradeService;
import org.knowm.xchange.exceptions.ExchangeException;

import java.io.IOException;
import java.util.List;

public class BybitExchange extends BaseExchange {

	@Override
	protected void initServices() {
		marketDataService = new BybitMarketDataService(this);
		tradeService = new BybitTradeService(this);
		accountService = new BybitAccountService(this);
	}

	@Override
	public void remoteInit() throws IOException, ExchangeException {
		//initialize currency pairs
		List<BybitSymbol> symbols = ((BybitMarketDataServiceRaw) marketDataService).getSymbols().getResult();
		symbols.forEach(bybitSymbol -> exchangeMetaData.getInstruments().put(
				MarketDataMapper.symbolToCurrencyPair(bybitSymbol),
				MarketDataMapper.symbolToCurrencyPairMetaData(bybitSymbol))
		);
	}

	@Override
	public ExchangeSpecification getDefaultExchangeSpecification() {
		ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
		exchangeSpecification.setSslUri("https://api.bybit.com");
		exchangeSpecification.setHost("bybit.com");
		exchangeSpecification.setPort(80);
		exchangeSpecification.setExchangeName("Bybit");
		exchangeSpecification.setExchangeDescription("BYBIT");
		return exchangeSpecification;
	}
}