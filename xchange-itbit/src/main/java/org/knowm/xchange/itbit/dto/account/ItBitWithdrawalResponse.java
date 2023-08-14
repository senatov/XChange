package org.knowm.xchange.itbit.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ItBitWithdrawalResponse {

	private final String id;
	private final String currency;
	private final BigDecimal amount;
	private final String address;
	private final String completionDate;

	public ItBitWithdrawalResponse(
			@JsonProperty("id") String id,
			@JsonProperty("currency") String currency,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("address") String address,
			@JsonProperty("completionDate") String completionDate) {
		this.id = id;
		this.currency = currency;
		this.amount = amount;
		this.address = address;
		this.completionDate = completionDate;
	}

	public String getId() {
		return id;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getAddress() {
		return address;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	@Override
	public String toString() {
		String builder = "ItBitWithdrawalResponse [id=" +
				id +
				", currency=" +
				currency +
				", amount=" +
				amount +
				", address=" +
				address +
				", completionDate=" +
				completionDate +
				"]";
		return builder;
	}
}