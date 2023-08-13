package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderType;
import org.knowm.xchange.kraken.dto.trade.KrakenType;

import java.math.BigDecimal;

public record KrakenPublicTrade(BigDecimal price, BigDecimal volume, double time, KrakenType type,
                                KrakenOrderType orderType, String miscellaneous, String tradeId) {

	public KrakenPublicTrade(
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("volume") BigDecimal volume,
			@JsonProperty("time") double time,
			@JsonProperty("type") KrakenType type,
			@JsonProperty("orderType") KrakenOrderType orderType,
			@JsonProperty("miscellaneous") String miscellaneous, @JsonProperty("trade_id") String tradeId) {
		this.price = price;
		this.volume = volume;
		this.time = time;
		this.type = type;
		this.orderType = orderType;
		this.miscellaneous = miscellaneous;
		this.tradeId = tradeId;
	}


	@Override
	public String toString() {
		return "KrakenPublicTrade [price="
				+ price
				+ ", volume="
				+ volume
				+ ", time="
				+ time
				+ ", type="
				+ type
				+ ", orderType="
				+ orderType
				+ ", miscellaneous="
				+ miscellaneous
				+ ", trade_id=" + tradeId
				+ "]";
	}
}