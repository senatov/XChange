package org.knowm.xchange.coindeal.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coindeal.CoindealAdapters;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;

public class CoindealAccountService extends CoindealAccountServiceRaw implements AccountService {

	public CoindealAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return CoindealAdapters.adaptToAccountInfo(getCoindealBalances());
	}
}