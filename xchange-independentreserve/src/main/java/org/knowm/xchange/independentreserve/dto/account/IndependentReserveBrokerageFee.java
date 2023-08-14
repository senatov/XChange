package org.knowm.xchange.independentreserve.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class IndependentReserveBrokerageFee {

	private final String currencyCode;

	private final BigDecimal fee;

	public IndependentReserveBrokerageFee(
			@JsonProperty("CurrencyCode") String currencyCode, @JsonProperty("Fee") BigDecimal fee) {
		this.currencyCode = currencyCode;
		this.fee = fee;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public BigDecimal getFee() {
		return fee;
	}
}