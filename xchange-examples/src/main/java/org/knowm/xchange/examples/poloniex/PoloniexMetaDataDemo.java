package org.knowm.xchange.examples.poloniex;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.poloniex.PoloniexExchange;

import java.io.IOException;

public class PoloniexMetaDataDemo {

	public static void main(String[] args) throws IOException {
		// Use the factory to get Poloniex exchange API using default settings
		Exchange anx = ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class);
		ExchangeMetaData exchangeMetaData = anx.getExchangeMetaData();
		System.out.println(exchangeMetaData.toJSONString());
		System.out.println(
				"private poll delay ms: "
						+ ExchangeMetaData.getPollDelayMillis(exchangeMetaData.getPrivateRateLimits()));
		System.out.println(
				"public  poll delay ms: "
						+ ExchangeMetaData.getPollDelayMillis(exchangeMetaData.getPublicRateLimits()));
	}
}