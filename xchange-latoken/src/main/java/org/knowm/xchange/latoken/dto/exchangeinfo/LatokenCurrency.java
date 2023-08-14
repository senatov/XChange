package org.knowm.xchange.latoken.dto.exchangeinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response schema:
 * <pre>
 * {
 * 	"currencyId": 102,
 * 	"symbol": "LA",
 * 	"name": "Latoken",
 * 	"precission": 8,
 * 	"type": "ERC20",
 * 	"fee": 0.1
 * }
 * </pre>
 *
 * @author Ezer
 */
public class LatokenCurrency {

	private final long currencyId;
	private final String symbol;
	private final String name;
	private final int precision;
	private final String type;
	private final BigDecimal fee;

	/**
	 * C'tor
	 */
	public LatokenCurrency(
			@JsonProperty("currencyId") long currencyId,
			@JsonProperty("symbol") String symbol,
			@JsonProperty("name") String name,
			@JsonProperty("precission") int precision,
			@JsonProperty("type") String type,
			@JsonProperty("fee") BigDecimal fee) {
		this.currencyId = currencyId;
		this.symbol = symbol;
		this.name = name;
		this.precision = precision;
		this.type = type;
		this.fee = fee;
	}

	/**
	 * ID of currency
	 */
	public long getCurrencyId() {
		return currencyId;
	}

	/**
	 * Symbol of currency
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Name of currency
	 */
	public String getName() {
		return name;
	}

	/**
	 * Precision of currency
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * Type of currency (e.g. "ERC20")
	 */
	public String getType() {
		return type;
	}

	/**
	 * Transaction fee for currency
	 */
	public BigDecimal getFee() {
		return fee;
	}

	@Override
	public String toString() {
		return "LatokenCurrency [currencyId = "
				+ currencyId
				+ ", symbol = "
				+ symbol
				+ ", name = "
				+ name
				+ ", precision = "
				+ precision
				+ ", type = "
				+ type
				+ ", fee = "
				+ fee
				+ "]";
	}
}