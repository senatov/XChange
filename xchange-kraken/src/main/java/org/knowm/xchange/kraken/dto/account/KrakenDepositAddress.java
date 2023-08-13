package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KrakenDepositAddress(String address, String tag, Long expiretm, boolean newAddress) {

	public KrakenDepositAddress(
			@JsonProperty("address") String address,
			@JsonProperty("tag") String tag,
			@JsonProperty("expiretm") Long expiretm,
			@JsonProperty("new") boolean newAddress) {
		this.address = address;
		this.tag = tag;
		this.expiretm = expiretm;
		this.newAddress = newAddress;
	}


	@Override
	public String toString() {
		return "KrakenDepositAddress [address="
				+ address
				+ ", tag="
				+ tag
				+ ", expiretm="
				+ expiretm
				+ ", newAddress="
				+ newAddress
				+ "]";
	}
}