package org.knowm.xchange.examples.bitcoincharts;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitcoincharts.BitcoinChartsExchange;

import java.io.IOException;

public class BitcoinChartsMetaDataDemo {

	public static void main(String[] args) throws IOException {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitcoinChartsExchange.class);
		exchange.remoteInit();
		System.out.println(exchange.getExchangeMetaData().toString());
	}
}