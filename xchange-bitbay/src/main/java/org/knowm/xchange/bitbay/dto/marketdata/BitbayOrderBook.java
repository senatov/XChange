package org.knowm.xchange.bitbay.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author kpysniak
 */
public class BitbayOrderBook {

	private final BigDecimal[][] asks;
	private final BigDecimal[][] bids;

	/**
	 * Constructor
	 */
	public BitbayOrderBook(
			@JsonProperty("asks") BigDecimal[][] asks, @JsonProperty("bids") BigDecimal[][] bids) {
		this.asks = asks;
		this.bids = bids;
	}

	@Override
	public String toString() {
		StringBuilder asksBuilder = new StringBuilder();
		StringBuilder bidsBuilder = new StringBuilder();
		for (BigDecimal[] ask : getAsks()) {
			asksBuilder.append(Arrays.toString(ask)).append(";");
		}
		for (BigDecimal[] bid : getBids()) {
			bidsBuilder.append(Arrays.toString(bid)).append(";");
		}
		return "BitbayOrderBook{" + "asks=" + asksBuilder + ", bids=" + bidsBuilder + '}';
	}

	public BigDecimal[][] getAsks() {
		return asks;
	}

	public BigDecimal[][] getBids() {
		return bids;
	}
}