package org.knowm.xchange.itbit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class ItBitTrades {

	private final int count;
	private final ItBitTrade[] trades;

	public ItBitTrades(
			@JsonProperty("count") int count, @JsonProperty("recentTrades") ItBitTrade[] trades) {
		this.count = count;
		this.trades = trades;
	}

	public int getCount() {
		return count;
	}

	public ItBitTrade[] getTrades() {
		return trades;
	}

	@Override
	public String toString() {
		String builder = "ItBitTrades [count=" +
				count +
				", trades=" +
				Arrays.toString(trades) +
				"]";
		return builder;
	}
}