package org.knowm.xchange.bitfinex.v2.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.knowm.xchange.dto.Order.OrderType;

import java.math.BigDecimal;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class BitfinexPublicTrade {

	private long tradeId;
	private long timestamp;
	private BigDecimal amount;
	private BigDecimal price;

	public BitfinexPublicTrade() {
	}

	public BitfinexPublicTrade(long tradeId, long timestamp, BigDecimal amount, BigDecimal price) {
		this.tradeId = tradeId;
		this.timestamp = timestamp;
		this.amount = amount;
		this.price = price;
	}

	public long getTradeId() {
		return tradeId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public OrderType getType() {
		return getAmount().signum() == -1 ? OrderType.ASK : OrderType.BID;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		String builder = "BitfinexPublicTrade [tradeId=" +
				tradeId +
				", timestamp=" +
				timestamp +
				", amount=" +
				amount +
				", price=" +
				price +
				"]";
		return builder;
	}
}