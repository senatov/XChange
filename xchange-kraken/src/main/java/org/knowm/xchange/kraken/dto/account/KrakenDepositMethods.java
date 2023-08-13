package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

public class KrakenDepositMethods {
	/*
	 * method = name of deposit method limit = maximum net amount that can be deposited right now fee
	 * = amount of fees that will be paid address-setup-fee = whether or not method has an address
	 * setup fee (optional)
	 */

	@Getter
	private final String method;
	private final String limit;
	@Getter
	private final BigDecimal fee;
	@Getter
	private final BigDecimal addressSetupFee;

	public KrakenDepositMethods(
			@JsonProperty("method") String method,
			@JsonProperty("limit") String limit,
			@JsonProperty("fee") BigDecimal fee,
			@JsonProperty("address-setup-fee") BigDecimal addressSetupFee) {
		super();
		this.method = method;
		this.limit = limit;
		this.fee = fee;
		this.addressSetupFee = addressSetupFee;
	}

	public BigDecimal getLimit() {
		if (limit.equals("false")) {
			return BigDecimal.valueOf(Double.MAX_VALUE);
		} else {
			return new BigDecimal(limit);
		}
	}

	@Override
	public String toString() {
		return "KrakenDepositMethods [method="
				+ method
				+ ", limit="
				+ limit
				+ ", fee="
				+ fee
				+ ", addressSetupFee="
				+ addressSetupFee
				+ "]";
	}
}