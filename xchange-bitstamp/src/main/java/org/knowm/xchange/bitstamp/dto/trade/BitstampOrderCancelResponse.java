package org.knowm.xchange.bitstamp.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.currency.CurrencyPair;

import java.math.BigDecimal;

public class BitstampOrderCancelResponse {

	private final String error;
	private final long id;
	/**
	 * 0 - buy (bid); 1 - sell (ask)
	 */
	private final int type;
	private final CurrencyPair currencyPair;
	private final BigDecimal price;
	private final BigDecimal amount;

	/**
	 * @param id Order id
	 * @param amount Order amount
	 * @param price Order price
	 * @param type Order type
	 * @param error Order error
	 */
	public BitstampOrderCancelResponse(
			@JsonProperty("id") long id,
			@JsonProperty("type") int type,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("currency_pair") CurrencyPair currencyPair,
			@JsonProperty("error") String error) {
		this.id = id;
		this.type = type;
		this.price = price;
		this.amount = amount;
		this.currencyPair = currencyPair;
		this.error = error;
	}

	public long getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public CurrencyPair getCurrencyPair() {
		return currencyPair;
	}

	public String getError() {
		return error;
	}
}