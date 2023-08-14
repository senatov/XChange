package org.knowm.xchange.upbit.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.upbit.UpbitAdapters;

import java.io.IOException;

public class UpbitAccountService extends UpbitAccountServiceRaw implements AccountService {

	/**
	 * Constructor
	 */
	public UpbitAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return new AccountInfo(UpbitAdapters.adaptWallet(super.getWallet()));
	}
}