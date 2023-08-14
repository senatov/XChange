package org.knowm.xchange.coindirect.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coindirect.dto.CoindirectException;
import org.knowm.xchange.coindirect.dto.account.CoindirectAccountChannel;
import org.knowm.xchange.coindirect.dto.account.CoindirectWallet;

import java.io.IOException;
import java.util.List;

public class CoindirectAccountServiceRaw extends CoindirectBaseService {
	/**
	 * Constructor
	 */
	public CoindirectAccountServiceRaw(Exchange exchange) {
		super(exchange);
	}

	public List<CoindirectWallet> listCoindirectWallets(long max)
			throws IOException, CoindirectException {
		return coindirect.listWallets(max, signatureCreator);
	}

	public CoindirectAccountChannel getAccountChannel() throws IOException, CoindirectException {
		return coindirect.getAccountChannel(signatureCreator);
	}
}