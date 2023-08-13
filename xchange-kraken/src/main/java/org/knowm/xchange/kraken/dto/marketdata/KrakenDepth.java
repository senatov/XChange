package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Data object representing depth from Kraken
 */
public record KrakenDepth(List<KrakenPublicOrder> asks, List<KrakenPublicOrder> bids) {

	/**
	 * Constructor
	 */
	public KrakenDepth(
			@JsonProperty("asks") List<KrakenPublicOrder> asks,
			@JsonProperty("bids") List<KrakenPublicOrder> bids) {
		this.asks = asks;
		this.bids = bids;
	}


	@Override
	public String toString() {
		return "KrakenDepth [asks=" + asks + ", bids=" + bids + "]";
	}
}