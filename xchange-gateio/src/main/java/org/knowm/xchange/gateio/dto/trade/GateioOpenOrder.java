package org.knowm.xchange.gateio.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class GateioOpenOrder {

	private final String timestamp;

	private final String total;

	private final BigDecimal amount;

	private final String id;

	private final BigDecimal rate;

	private final String status;

	private final String orderNumber;

	private final String type;

	private final String currencyPair;

	/**
	 * Constructor
	 */
	private GateioOpenOrder(
			@JsonProperty("timestamp") String timestamp,
			@JsonProperty("total") String total,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("id") String id,
			@JsonProperty("rate") BigDecimal rate,
			@JsonProperty("status") String status,
			@JsonProperty("orderNumber") String orderNumber,
			@JsonProperty("type") String type,
			@JsonProperty("currencyPair") String currencyPair) {
		this.timestamp = timestamp;
		this.total = total;
		this.amount = amount;
		this.id = id;
		this.rate = rate;
		this.status = status;
		this.orderNumber = orderNumber;
		this.type = type;
		this.currencyPair = currencyPair;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getTotal() {
		return total;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public String getStatus() {
		return status;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getType() {
		return type;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	@Override
	public String toString() {
		return "GateioOpenOrder{"
				+ "timestamp='"
				+ timestamp
				+ '\''
				+ ", total='"
				+ total
				+ '\''
				+ ", amount='"
				+ amount
				+ '\''
				+ ", id='"
				+ id
				+ '\''
				+ ", rate='"
				+ rate
				+ '\''
				+ ", status='"
				+ status
				+ '\''
				+ ", orderNumber='"
				+ orderNumber
				+ '\''
				+ ", type='"
				+ type
				+ '\''
				+ ", currencyPair='"
				+ currencyPair
				+ '\''
				+ '}';
	}
}