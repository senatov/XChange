package org.knowm.xchange.coinfloor.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinfloor.dto.account.CoinfloorBalance;
import org.knowm.xchange.instrument.Instrument;

import java.io.IOException;

public class CoinfloorAccountServiceRaw extends CoinfloorAuthenticatedService {

	protected CoinfloorAccountServiceRaw(Exchange exchange) {
		super(exchange);
	}

	public CoinfloorBalance getCoinfloorBalance(Instrument pair) throws IOException {
		return coinfloor.getBalance(normalise(pair.getBase()), normalise(pair.getCounter()));
	}
}