package org.knowm.xchange.upbit.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.upbit.dto.UpbitException;
import org.knowm.xchange.upbit.dto.account.UpbitBalances;

import java.io.IOException;

public class UpbitAccountServiceRaw extends UpbitBaseService {

	/**
	 * Constructor
	 */
	public UpbitAccountServiceRaw(Exchange exchange) {
		super(exchange);
	}

	public UpbitBalances getWallet() throws UpbitException, IOException {
		return upbit.getWallet(this.signatureCreator);
	}
}