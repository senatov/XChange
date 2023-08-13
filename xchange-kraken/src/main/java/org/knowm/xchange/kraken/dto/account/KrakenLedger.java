package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record KrakenLedger(String refId, double unixTime, LedgerType ledgerType, String assetClass, String asset,
                           BigDecimal transactionAmount, BigDecimal fee, BigDecimal balance) {

	/**
	 * Constructor
	 */
	public KrakenLedger(
			@JsonProperty("refid") String refId,
			@JsonProperty("time") double unixTime,
			@JsonProperty("type") LedgerType ledgerType,
			@JsonProperty("aclass") String assetClass,
			@JsonProperty("asset") String asset,
			@JsonProperty("amount") BigDecimal transactionAmount,
			@JsonProperty("fee") BigDecimal fee,
			@JsonProperty("balance") BigDecimal balance) {
		this.refId = refId;
		this.unixTime = unixTime;
		this.ledgerType = ledgerType;
		this.assetClass = assetClass;
		this.asset = asset;
		this.transactionAmount = transactionAmount;
		this.fee = fee;
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "KrakenLedgerInfo [refId="
				+ refId
				+ ", unixTime="
				+ unixTime
				+ ", ledgerType="
				+ ledgerType
				+ ", assetClass="
				+ assetClass
				+ ", asset="
				+ asset
				+ ", transactionAmount="
				+ transactionAmount
				+ ", fee="
				+ fee
				+ ", balance="
				+ balance
				+ "]";
	}
}