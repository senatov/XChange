package org.knowm.xchange.coingi.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CoingiBalance {
	private final CoingiCurrency currency;

	private final BigDecimal available;

	private final BigDecimal inOrders;

	private final BigDecimal deposited;

	private final BigDecimal withdrawing;

	private final BigDecimal blocked;

	public CoingiBalance(
			@JsonProperty("currency") CoingiCurrency currency,
			@JsonProperty("available") BigDecimal available,
			@JsonProperty("inOrders") BigDecimal inOrders,
			@JsonProperty("deposited") BigDecimal deposited,
			@JsonProperty("withdrawing") BigDecimal withdrawing,
			@JsonProperty("blocked") BigDecimal blocked) {
		this.currency = currency;
		this.available = available;
		this.inOrders = inOrders;
		this.deposited = deposited;
		this.withdrawing = withdrawing;
		this.blocked = blocked;
	}

	public CoingiCurrency getCurrency() {
		return currency;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public BigDecimal getInOrders() {
		return inOrders;
	}

	public BigDecimal getDeposited() {
		return deposited;
	}

	public BigDecimal getWithdrawing() {
		return withdrawing;
	}

	public BigDecimal getBlocked() {
		return blocked;
	}
}