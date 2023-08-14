package org.knowm.xchange.krakenfutures.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.krakenfutures.dto.account.KrakenFuturesAccounts;

import java.io.IOException;

/**
 * @author Jean-Christophe Laruelle
 */
public class KrakenFuturesAccountServiceRaw extends KrakenFuturesBaseService {

	/**
	 * Constructor
	 */
	public KrakenFuturesAccountServiceRaw(Exchange exchange) {
		super(exchange);
	}

	public KrakenFuturesAccounts getKrakenFuturesAccounts() throws IOException {
		KrakenFuturesAccounts krakenFuturesAccounts =
				krakenFuturesAuthenticated.accounts(
						exchange.getExchangeSpecification().getApiKey(),
						signatureCreator,
						exchange.getNonceFactory());
		if (krakenFuturesAccounts.isSuccess()) {
			return krakenFuturesAccounts;
		} else {
			throw new ExchangeException(
					"Error getting CF accounts info: " + krakenFuturesAccounts.getError());
		}
	}
}