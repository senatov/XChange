package org.knowm.xchange.kraken.dto.marketdata;

import java.math.BigDecimal;

public record KrakenSpread(long time, BigDecimal bid, BigDecimal ask) {


	@Override
	public String toString() {
		return "KrakenSpread [time=" + time + ", bid=" + bid + ", ask=" + ask + "]";
	}
}