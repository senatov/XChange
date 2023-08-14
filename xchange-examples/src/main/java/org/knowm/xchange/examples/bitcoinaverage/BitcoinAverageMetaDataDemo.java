package org.knowm.xchange.examples.bitcoinaverage;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitcoinaverage.BitcoinAverageExchange;

import java.io.IOException;

public class BitcoinAverageMetaDataDemo {

	public static void main(String[] args) throws IOException {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitcoinAverageExchange.class);
		exchange.remoteInit();
		System.out.println(exchange.getExchangeMetaData().toString());
	}
}