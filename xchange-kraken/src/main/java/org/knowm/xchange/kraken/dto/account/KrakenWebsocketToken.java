package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KrakenWebsocketToken(@JsonProperty("token") String token, @JsonProperty("expires") int expiresInSeconds) {

	@Override
	public String token() {
		return token;
	}

	@Override
	public int expiresInSeconds() {
		return expiresInSeconds;
	}

	@Override
	public String toString() {
		return "KrakenWebsocketToken{"
				+ "token='"
				+ token
				+ '\''
				+ ", expiresInSeconds="
				+ expiresInSeconds
				+ '}';
	}
}