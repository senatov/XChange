package org.knowm.xchange.bitfinex.v1.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BitfinexBalancesResponse {

	private final String type;
	private final String currency;
	private final BigDecimal amount;
	private final BigDecimal available;

	/**
	 * Constructor
	 */
	public BitfinexBalancesResponse(
			@JsonProperty("type") String type,
			@JsonProperty("currency") String currency,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("available") BigDecimal available) {
		this.type = type;
		this.currency = currency;
		this.amount = amount;
		this.available = available;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public String getCurrency() {
		return currency;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		String builder = "BitfinexBalancesResponse [type=" +
				type +
				", currency=" +
				currency +
				", amount=" +
				amount +
				", available=" +
				available +
				"]";
		return builder;
	}
}