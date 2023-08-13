package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @param tradeBalance trade balance (combined balance of all currencies)
 * @param margin initial margin amount of open positions
 * @param unrealizedGainsLosses unrealized net profit/loss of open positions
 * @param costBasis cost basis of open positions
 * @param floatingValuation current floating valuation of open positions
 * @param equity trade balance + unrealized net profit/loss
 * @param freeMargin equity - initial margin (maximum margin available to open new positions)
 * @param marginLevel (equity / initial margin) * 100
 */
public record KrakenTradeBalanceInfo(BigDecimal tradeBalance, BigDecimal margin, BigDecimal unrealizedGainsLosses,
                                     BigDecimal costBasis, BigDecimal floatingValuation, BigDecimal equity,
                                     BigDecimal freeMargin, BigDecimal marginLevel) {

	/**
	 * Constructor
	 */
	public KrakenTradeBalanceInfo(
			@JsonProperty("tb") BigDecimal tradeBalance,
			@JsonProperty("m") BigDecimal margin,
			@JsonProperty("n") BigDecimal unrealizedGainsLosses,
			@JsonProperty("c") BigDecimal costBasis,
			@JsonProperty("v") BigDecimal floatingValuation,
			@JsonProperty("e") BigDecimal equity,
			@JsonProperty("mf") BigDecimal freeMargin,
			@JsonProperty("ml") BigDecimal marginLevel) {
		this.tradeBalance = tradeBalance;
		this.margin = margin;
		this.unrealizedGainsLosses = unrealizedGainsLosses;
		this.costBasis = costBasis;
		this.floatingValuation = floatingValuation;
		this.equity = equity;
		this.freeMargin = freeMargin;
		this.marginLevel = marginLevel;
	}


	@Override
	public String toString() {
		return "KrakenTradeBalanceInfo [tradeBalance="
				+ tradeBalance
				+ ", margin="
				+ margin
				+ ", unrealizedGainsLosses="
				+ unrealizedGainsLosses
				+ ", costBasis="
				+ costBasis
				+ ", floatingValuation="
				+ floatingValuation
				+ ", equity="
				+ equity
				+ ", freeMargin="
				+ freeMargin
				+ ", marginLevel="
				+ marginLevel
				+ "]";
	}
}