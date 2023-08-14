package info.bitrich.xchangestream.poloniex2.dto;

import org.knowm.xchange.dto.Order;

import java.math.BigDecimal;

/**
 * Created by Lukas Zaoralek on 11.11.17.
 */
public class TradeEvent {
	private final String tradeId;
	private final Order.OrderType type;
	private final BigDecimal price;
	private final BigDecimal size;
	private final int timestampSeconds;

	public TradeEvent(
			String tradeId,
			Order.OrderType type,
			BigDecimal price,
			BigDecimal size,
			int timestampSeconds) {
		this.tradeId = tradeId;
		this.type = type;
		this.price = price;
		this.size = size;
		this.timestampSeconds = timestampSeconds;
	}

	public String getTradeId() {
		return tradeId;
	}

	public Order.OrderType getType() {
		return type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getSize() {
		return size;
	}

	public int getTimestampSeconds() {
		return timestampSeconds;
	}
}