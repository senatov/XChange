package org.knowm.xchange.kraken.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record KrakenOrderDescription(String assetPair, KrakenType type, KrakenOrderType orderType, BigDecimal price,
                                     BigDecimal secondaryPrice, String leverage, String positionTxId,
                                     String orderDescription, String closeDescription) {

	/**
	 * Constructor
	 */
	public KrakenOrderDescription(
			@JsonProperty("pair") String assetPair,
			@JsonProperty("type") KrakenType type,
			@JsonProperty("ordertype") KrakenOrderType orderType,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("price2") BigDecimal secondaryPrice,
			@JsonProperty("leverage") String leverage,
			@JsonProperty("position") String positionTxId,
			@JsonProperty("order") String orderDescription,
			@JsonProperty("close") String closeDescription) {
		this.assetPair = assetPair;
		this.type = type;
		this.orderType = orderType;
		this.price = price;
		this.secondaryPrice = secondaryPrice;
		this.leverage = leverage;
		this.positionTxId = positionTxId;
		this.orderDescription = orderDescription;
		this.closeDescription = closeDescription;
	}


	@Override
	public String toString() {
		return "KrakenOrderDescription [assetPair="
				+ assetPair
				+ ", type="
				+ type
				+ ", orderType="
				+ orderType
				+ ", price="
				+ price
				+ ", secondaryPrice="
				+ secondaryPrice
				+ ", leverage="
				+ leverage
				+ ", positionTxId="
				+ positionTxId
				+ ", orderDescription="
				+ orderDescription
				+ ", closeDescription="
				+ closeDescription
				+ "]";
	}
}