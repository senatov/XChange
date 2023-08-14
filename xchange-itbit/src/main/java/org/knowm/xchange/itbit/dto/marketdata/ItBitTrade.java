package org.knowm.xchange.itbit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ItBitTrade {

	private final BigDecimal amount;
	private final String timestamp;
	private final BigDecimal price;
	private final long matchNumber;

	public ItBitTrade(
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("timestamp") String timestamp,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("matchNumber") long matchNumber) {
		this.amount = amount;
		this.timestamp = timestamp;
		this.price = price;
		this.matchNumber = matchNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public long getMatchNumber() {
		return matchNumber;
	}

	@Override
	public String toString() {
		String builder = "ItBitTrade [amount=" +
				amount +
				", timestamp=" +
				timestamp +
				", price=" +
				price +
				", matchNumber=" +
				matchNumber +
				"]";
		return builder;
	}
}