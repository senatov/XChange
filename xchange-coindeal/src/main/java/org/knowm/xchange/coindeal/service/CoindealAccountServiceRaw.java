package org.knowm.xchange.coindeal.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coindeal.CoindealErrorAdapter;
import org.knowm.xchange.coindeal.dto.CoindealException;
import org.knowm.xchange.coindeal.dto.account.CoindealBalance;

import java.io.IOException;
import java.util.List;

public class CoindealAccountServiceRaw extends CoindealBaseService {

	public CoindealAccountServiceRaw(Exchange exchange) {
		super(exchange);
	}

	public List<CoindealBalance> getCoindealBalances() throws IOException, CoindealException {
		try {
			return coindeal.getBalances(basicAuthentication);
		} catch (CoindealException e) {
			throw CoindealErrorAdapter.adapt(e);
		}
	}
}