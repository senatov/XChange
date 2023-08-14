package org.knowm.xchange.ccex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CCEXBuySellData {

	private final BigDecimal quantity;
	private final BigDecimal rate;

	/**
	 *
	 */
	public CCEXBuySellData(
			@JsonProperty("Quantity") BigDecimal quantity, @JsonProperty("Rate") BigDecimal rate) {
		this.quantity = quantity;
		this.rate = rate;
	}

	/**
	 * @return The quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * @return The rate
	 */
	public BigDecimal getRate() {
		return rate;
	}
}