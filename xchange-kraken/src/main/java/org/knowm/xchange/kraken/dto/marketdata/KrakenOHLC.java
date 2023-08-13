package org.knowm.xchange.kraken.dto.marketdata;

import java.math.BigDecimal;

public record KrakenOHLC(long time, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal vwap,
                         BigDecimal volume, long count) {


	@Override
	public String toString() {
		return "KrakenOHLC [time="
				+ time
				+ ", open="
				+ open
				+ ", high="
				+ high
				+ ", low="
				+ low
				+ ", close="
				+ close
				+ ", vwap="
				+ vwap
				+ ", volume="
				+ volume
				+ ", count="
				+ count
				+ "]";
	}
}