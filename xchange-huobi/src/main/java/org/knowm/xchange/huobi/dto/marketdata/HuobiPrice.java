package org.knowm.xchange.huobi.dto.marketdata;

import java.math.BigDecimal;

public class HuobiPrice {

	private final BigDecimal price;
	private final BigDecimal volume;

	HuobiPrice(BigDecimal[] inputData) {
		this.price = inputData[0];
		this.volume = inputData[1];
	}

	@Override
	public String toString() {
		return String.format("HuobiPrice [price=%s, volume=%s]", getPrice(), getVolume());
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getVolume() {
		return volume;
	}
}