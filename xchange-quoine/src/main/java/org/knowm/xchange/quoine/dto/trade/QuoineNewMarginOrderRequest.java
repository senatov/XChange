package org.knowm.xchange.quoine.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class QuoineNewMarginOrderRequest extends QuoineNewOrderRequest {
	@JsonProperty("leverage_level")
	private final int leverageLevel;

	@JsonProperty("funding_currency")
	private final String fundingCurrency;

	@JsonProperty("order_direction")
	private final String orderDirection;

	public QuoineNewMarginOrderRequest(
			String orderType,
			int productCode,
			String side,
			BigDecimal quantity,
			BigDecimal price,
			int leverageLevel,
			String fundingCurrency) {
		super(orderType, productCode, side, quantity, price);
		this.leverageLevel = leverageLevel;
		this.fundingCurrency = fundingCurrency;
		this.orderDirection = "netout";
	}

	public int getLeverageLevel() {
		return leverageLevel;
	}

	public String getFundingCurrency() {
		return fundingCurrency;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	@Override
	public String toString() {
		String builder = "QuoineNewMarginOrderRequest [leverageLevel=" +
				leverageLevel +
				", getFundingCurrency()=" +
				fundingCurrency +
				", getOrderType()=" +
				getOrderType() +
				", getProductId()=" +
				getProductId() +
				", getSide()=" +
				getSide() +
				", getQuantity()=" +
				getQuantity() +
				", getPrice()=" +
				getPrice() +
				", toString()=" +
				super.toString() +
				", getClass()=" +
				getClass() +
				", hashCode()=" +
				hashCode() +
				"]";
		return builder;
	}
}