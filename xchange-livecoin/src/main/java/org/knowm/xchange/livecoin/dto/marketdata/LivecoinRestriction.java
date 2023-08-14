package org.knowm.xchange.livecoin.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class LivecoinRestriction {

	private final String currencyPair;
	private final BigDecimal minLimitQuantity;
	private final Integer priceScale;

	public LivecoinRestriction(
			@JsonProperty("currencyPair") String currencyPair,
			@JsonProperty("minLimitQuantity") BigDecimal minLimitQuantity,
			@JsonProperty("priceScale") Integer priceScale) {
		super();
		this.currencyPair = currencyPair;
		this.minLimitQuantity = minLimitQuantity;
		this.priceScale = priceScale;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public BigDecimal getMinLimitQuantity() {
		return minLimitQuantity;
	}

	public Integer getPriceScale() {
		return priceScale;
	}
}