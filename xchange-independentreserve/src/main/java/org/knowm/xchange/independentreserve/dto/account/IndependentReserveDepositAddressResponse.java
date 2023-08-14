package org.knowm.xchange.independentreserve.dto.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IndependentReserveDepositAddressResponse {
	private final String depositAddress;
	private final String lastCheckedTimestampUtc;
	private final String nextUpdateTimestampUtc;

	@JsonCreator
	public IndependentReserveDepositAddressResponse(
			@JsonProperty("DepositAddress") String depositAddress,
			@JsonProperty("LastCheckedTimestampUtc") String lastCheckedTimestampUtc,
			@JsonProperty("NextUpdateTimestampUtc") String nextUpdateTimestampUtc) {
		this.depositAddress = depositAddress;
		this.lastCheckedTimestampUtc = lastCheckedTimestampUtc;
		this.nextUpdateTimestampUtc = nextUpdateTimestampUtc;
	}

	public String getDepositAddress() {
		return depositAddress;
	}

	public String getLastCheckedTimestampUtc() {
		return lastCheckedTimestampUtc;
	}

	public String getNextUpdateTimestampUtc() {
		return nextUpdateTimestampUtc;
	}
}