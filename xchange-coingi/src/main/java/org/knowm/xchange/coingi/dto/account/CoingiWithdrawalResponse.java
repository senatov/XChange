package org.knowm.xchange.coingi.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoingiWithdrawalResponse {
	private final boolean result;

	public CoingiWithdrawalResponse(@JsonProperty("result") boolean result) {
		this.result = result;
	}
}