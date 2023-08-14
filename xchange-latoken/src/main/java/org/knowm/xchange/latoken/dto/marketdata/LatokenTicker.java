package org.knowm.xchange.latoken.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response schema:
 * <pre>
 * {
 * 	"pairId": 502,
 * 	"symbol": "LAETH",
 * 	"volume": 1023314.3202,
 * 	"open": 134.82,
 * 	"low": 133.95,
 * 	"high": 136.22,
 * 	"close": 135.12,
 * 	"priceChange": 0.22
 * }
 * </pre>
 *
 * @author Ezer
 */
public final class LatokenTicker {

	private final long pairId;
	private final String symbol;
	private final BigDecimal volume;
	private final BigDecimal open;
	private final BigDecimal low;
	private final BigDecimal high;
	private final BigDecimal close;
	private final BigDecimal priceChange;

	/**
	 * C'tor
	 */
	public LatokenTicker(
			@JsonProperty("pairId") long pairId,
			@JsonProperty("symbol") String symbol,
			@JsonProperty("volume") BigDecimal volume,
			@JsonProperty("open") BigDecimal open,
			@JsonProperty("low") BigDecimal low,
			@JsonProperty("high") BigDecimal high,
			@JsonProperty("close") BigDecimal close,
			@JsonProperty("priceChange") BigDecimal priceChange) {
		this.pairId = pairId;
		this.symbol = symbol;
		this.volume = volume;
		this.open = open;
		this.low = low;
		this.high = high;
		this.close = close;
		this.priceChange = priceChange;
	}

	/**
	 * ID of trading pair
	 */
	public long getPairId() {
		return pairId;
	}

	/**
	 * Trading pair symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Traded volume in last 24h
	 */
	public BigDecimal getVolume() {
		return volume;
	}

	/**
	 * Open price of ticker
	 */
	public BigDecimal getOpen() {
		return open;
	}

	/**
	 * Lowest price in last 24h
	 */
	public BigDecimal getLow() {
		return low;
	}

	/**
	 * Highest price in last 24h
	 */
	public BigDecimal getHigh() {
		return high;
	}

	/**
	 * Close price of ticker
	 */
	public BigDecimal getClose() {
		return close;
	}

	/**
	 * Change of price in last 24h (in percentage)
	 */
	public BigDecimal getPriceChange() {
		return priceChange;
	}

	@Override
	public String toString() {
		return "LatokenTicker [pairId = "
				+ pairId
				+ ", symbol = "
				+ symbol
				+ ", volume = "
				+ volume
				+ ", open = "
				+ open
				+ ", low = "
				+ low
				+ ", high = "
				+ high
				+ ", close = "
				+ close
				+ ", priceChange = "
				+ priceChange
				+ "]";
	}
}