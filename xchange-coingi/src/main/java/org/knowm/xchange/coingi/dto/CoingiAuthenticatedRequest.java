package org.knowm.xchange.coingi.dto;



public class CoingiAuthenticatedRequest {
	private String token;
	private Long nonce;
	private String signature;

	public CoingiAuthenticatedRequest setToken(String token) {
		this.token = token;
		return this;
	}

	public CoingiAuthenticatedRequest setNonce(Long nonce) {
		this.nonce = nonce;
		return this;
	}

	public CoingiAuthenticatedRequest setSignature(String signature) {
		this.signature = signature;
		return this;
	}
}