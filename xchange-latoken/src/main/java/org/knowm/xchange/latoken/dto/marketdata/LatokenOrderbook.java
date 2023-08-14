package org.knowm.xchange.latoken.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Response schema:
 * <pre>
 * {
 * 	"pairId": 502,
 * 	"symbol": "LAETH",
 * 	"spread": 0.07,
 * 	"asks": [
 *        {
 * 			"price": 136.3,
 * 			"amount": 7.024
 *        }
 * 	],
 * 	"bids": [
 *        {
 * 			"price": 136.2,
 * 			"amount": 6.554
 *        }
 * 	]
 * }
 * </pre>
 *
 * @author Ezer
 */
public final class LatokenOrderbook {

	private final long pairId;
	private final String symbol;
	private final BigDecimal spread;
	private final List<PriceLevel> asks;
	private final List<PriceLevel> bids;

	/**
	 * C'tor
	 */
	public LatokenOrderbook(
			@JsonProperty("pairId") long pairId,
			@JsonProperty("symbol") String symbol,
			@JsonProperty("spread") BigDecimal spread,
			@JsonProperty("asks") List<PriceLevel> asks,
			@JsonProperty("bids") List<PriceLevel> bids) {
		this.pairId = pairId;
		this.symbol = symbol;
		this.spread = spread;
		this.asks = asks;
		this.bids = bids;
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
	 * Spread between top bid and ask in percentage pair symbol
	 */
	public BigDecimal getSpread() {
		return spread;
	}

	/**
	 * Ask {@link PriceLevel levels}
	 */
	public List<PriceLevel> getAsks() {
		return asks;
	}

	/**
	 * Bid {@link PriceLevel levels}
	 */
	public List<PriceLevel> getBids() {
		return bids;
	}

	@Override
	public String toString() {
		return "LatokenOrderbook [pairId = "
				+ pairId
				+ ", symbol = "
				+ symbol
				+ ", spread = "
				+ spread
				+ ", asks = "
				+ asks
				+ ", bids = "
				+ bids
				+ "]";
	}
}