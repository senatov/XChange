package org.knowm.xchange.itbit.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ItBitOrder {

	private final String id;
	private final String walletId;
	private final String side;
	private final String instrument;
	private final String currency;
	private final String type;
	private final String status;
	private final String createdTime;
	private final BigDecimal amountFilled;
	private final BigDecimal price;
	private final BigDecimal amount;
	private final BigDecimal volumeWeightedAveragePrice;

	public ItBitOrder(
			@JsonProperty("id") String id,
			@JsonProperty("walletId") String walletId,
			@JsonProperty("side") String side,
			@JsonProperty("instrument") String instrument,
			@JsonProperty("currency") String currency,
			@JsonProperty("type") String type,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("amountFilled") BigDecimal amountFilled,
			@JsonProperty("volumeWeightedAveragePrice") BigDecimal volumeWeightedAveragePrice,
			@JsonProperty("createdTime") String createdTime,
			@JsonProperty("status") String status) {
		this.id = id;
		this.walletId = walletId;
		this.side = side;
		this.instrument = instrument;
		this.currency = currency;
		this.type = type;
		this.amount = amount;
		this.price = price;
		this.amountFilled = amountFilled;
		this.createdTime = createdTime;
		this.status = status;
		this.volumeWeightedAveragePrice = volumeWeightedAveragePrice;
	}

	public String getId() {
		return id;
	}

	public String getWalletId() {
		return walletId;
	}

	public String getSide() {
		return side;
	}

	public String getInstrument() {
		return instrument;
	}

	public String getCurrency() {
		return currency;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public BigDecimal getAmountFilled() {
		return amountFilled;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getVolumeWeightedAveragePrice() {
		return volumeWeightedAveragePrice;
	}

	@Override
	public String toString() {
		String builder = "ItBitAccountOrder [id=" +
				id +
				", walletId=" +
				walletId +
				", side=" +
				side +
				", instrument=" +
				instrument +
				", currency=" +
				currency +
				", type=" +
				type +
				", status=" +
				status +
				", createdTime=" +
				createdTime +
				", amountFilled=" +
				amountFilled +
				", price=" +
				price +
				", amount=" +
				amount +
				", volumeWeightedAveragePrice=" +
				volumeWeightedAveragePrice +
				"]";
		return builder;
	}
}