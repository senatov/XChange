package org.knowm.xchange.bankera.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bankera.BankeraAdapters;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;

public class BankeraAccountService extends BankeraAccountServiceRaw implements AccountService {

	public BankeraAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return BankeraAdapters.adaptAccountInfo(getUserInfo());
	}
}