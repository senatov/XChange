package org.knowm.xchange.bitstamp.dto.trade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.bitstamp.BitstampUtils;
import si.mazi.rescu.ExceptionalReturnContentException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Matija Mazi
 */
public final class BitstampOrder {

	private final long id;
	private final Date datetime;
	/**
	 * 0 - buy (bid); 1 - sell (ask)
	 */
	private final int type;

	private final BigDecimal price;
	private final BigDecimal amount;
	private final String currencyPair;
	private final String errorMessage;

	public BitstampOrder(
			@JsonProperty("status") String status,
			@JsonProperty("reason") Object reason,
			@JsonProperty("id") long id,
			@JsonProperty("datetime") String datetime,
			@JsonProperty("type") int type,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("currency_pair") String currencyPair,
			@JsonProperty("error") @JsonDeserialize(using = BitstampErrorDeserializer.class)
			String errorMessage) {
		if ("error".equals(status)) {
			throw new ExceptionalReturnContentException(String.valueOf(reason));
		}
		this.id = id;
		this.datetime = BitstampUtils.parseDate(datetime);
		this.type = type;
		this.price = price;
		this.amount = amount;
		this.currencyPair = currencyPair;
		this.errorMessage = errorMessage;
	}

	public Date getDatetime() {
		return datetime;
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

	public String getCurrencyPair() {
		return currencyPair;
	}

	@JsonIgnore
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return errorMessage != null
				? errorMessage
				: String.format(
				"Order{id=%s, datetime=%s, type=%s, price=%s, amount=%s, currencyPair=%s}",
				id, datetime, type, price, amount, currencyPair);
	}
}