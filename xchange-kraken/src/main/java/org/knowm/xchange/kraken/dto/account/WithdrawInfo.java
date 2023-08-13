package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record WithdrawInfo(String method, BigDecimal limit, String fee) {
	/*
	 * method = name of the withdrawal method that will be used limit = maximum net amount that can be withdrawn right now fee = amount of fees that
	 * will be paid
	 */

	public WithdrawInfo(
			@JsonProperty("method") String method,
			@JsonProperty("limit") BigDecimal limit,
			@JsonProperty("fee") String fee) {
		this.method = method;
		this.limit = limit;
		this.fee = fee;
	}


	@Override
	public String toString() {
		return "WithdrawInfo [method=" + method + ", limit=" + limit + ", fee=" + fee + "]";
	}
}