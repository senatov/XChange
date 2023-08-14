package org.knowm.xchange.bitfinex.v1.dto.account;

public class BitfinexBalancesRequest extends BitfinexEmptyRequest {

	/**
	 * Constructor
	 */
	public BitfinexBalancesRequest(String nonce) {
		super(nonce, "/v1/balances");
	}
}