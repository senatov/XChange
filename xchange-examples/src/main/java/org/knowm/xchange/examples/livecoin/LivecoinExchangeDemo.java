package org.knowm.xchange.examples.livecoin;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.livecoin.LivecoinExchange;

import java.io.IOException;

public class LivecoinExchangeDemo {

	public static void main(String[] args) throws IOException {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(LivecoinExchange.class);
		System.out.println("ExchangeMetaData toString(): " + exchange.getExchangeMetaData().toString());
		System.out.println(
				"ExchangeMetaData toJSONString(): " + exchange.getExchangeMetaData().toJSONString());
		System.out.println("Currency Pairs: " + exchange.getExchangeInstruments());
	}
}