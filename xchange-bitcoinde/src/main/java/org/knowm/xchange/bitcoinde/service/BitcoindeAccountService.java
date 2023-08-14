package org.knowm.xchange.bitcoinde.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcoinde.BitcoindeAdapters;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;

/**
 * @author kaiserfr
 */
public class BitcoindeAccountService extends BitcoindeAccountServiceRaw implements AccountService {

	public BitcoindeAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return BitcoindeAdapters.adaptAccountInfo(getBitcoindeAccount());
	}
}