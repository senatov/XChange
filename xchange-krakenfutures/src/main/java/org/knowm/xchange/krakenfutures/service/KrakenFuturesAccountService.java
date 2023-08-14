package org.knowm.xchange.krakenfutures.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.krakenfutures.KrakenFuturesAdapters;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;

/**
 * @author Jean-Christophe Laruelle
 */
public class KrakenFuturesAccountService extends KrakenFuturesAccountServiceRaw
		implements AccountService {

	/**
	 * Constructor
	 */
	public KrakenFuturesAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return KrakenFuturesAdapters.adaptAccounts(getKrakenFuturesAccounts(), getKrakenFuturesOpenPositions());
	}
}