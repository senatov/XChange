package org.knowm.xchange.therock.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class TheRockTrades {

	private final TheRockTrade[] trades;

	public TheRockTrades(
			@JsonProperty("trades") TheRockTrade[] trades, @JsonProperty("meta") Object ignored) {
		this.trades = trades;
	}

	public int getCount() {
		return trades.length;
	}

	public TheRockTrade[] getTrades() {
		return trades;
	}

	@Override
	public String toString() {
		String builder = "TheRockTrades [count=" +
				trades.length +
				", trades=" +
				Arrays.toString(trades) +
				"]";
		return builder;
	}
}