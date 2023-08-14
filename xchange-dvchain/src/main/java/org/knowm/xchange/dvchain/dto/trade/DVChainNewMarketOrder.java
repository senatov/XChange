package org.knowm.xchange.dvchain.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class DVChainNewMarketOrder {
	private final String side;
	private final BigDecimal qty;
	private final String asset;
	private final BigDecimal price;
	private final String orderType;

	public DVChainNewMarketOrder(
			@JsonProperty("side") String side,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("qty") BigDecimal qty,
			@JsonProperty("asset") String asset) {
		this.asset = asset;
		this.price = price;
		this.qty = qty;
		this.side = side;
		orderType = "market";
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public String getAsset() {
		return asset;
	}

	public String getSide() {
		return side;
	}

	public String getOrderType() {
		return orderType;
	}
}