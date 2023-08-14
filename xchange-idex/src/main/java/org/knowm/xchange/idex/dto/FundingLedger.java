package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class FundingLedger {

	private String depositNumber = "0";
	private String currency = "";
	private String amount = "";
	private String timestamp = "0";
	private String status;
	private String transactionHash = "";

	/**
	 *
	 */
	public FundingLedger depositNumber(String depositNumber) {
		this.depositNumber = depositNumber;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("depositNumber")
	public String getDepositNumber() {
		return depositNumber;
	}

	public void setDepositNumber(String depositNumber) {
		this.depositNumber = depositNumber;
	}

	/**
	 *
	 */
	public FundingLedger currency(String currency) {
		this.currency = currency;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 *
	 */
	public FundingLedger amount(String amount) {
		this.amount = amount;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("amount")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 *
	 */
	public FundingLedger timestamp(String timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("timestamp")
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 *
	 */
	public FundingLedger status(String status) {
		this.status = status;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 *
	 */
	public FundingLedger transactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("transactionHash")
	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}

	@Override
	public int hashCode() {
		return Objects.hash(depositNumber, currency, amount, timestamp, status, transactionHash);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundingLedger fundingLedger = (FundingLedger) o;
		return Objects.equals(depositNumber, fundingLedger.depositNumber)
				&& Objects.equals(currency, fundingLedger.currency)
				&& Objects.equals(amount, fundingLedger.amount)
				&& Objects.equals(timestamp, fundingLedger.timestamp)
				&& Objects.equals(status, fundingLedger.status)
				&& Objects.equals(transactionHash, fundingLedger.transactionHash);
	}

	@Override
	public String toString() {
		String sb = "class FundingLedger {\n" +
				"    depositNumber: " + toIndentedString(depositNumber) + "\n" +
				"    currency: " + toIndentedString(currency) + "\n" +
				"    amount: " + toIndentedString(amount) + "\n" +
				"    timestamp: " + toIndentedString(timestamp) + "\n" +
				"    status: " + toIndentedString(status) + "\n" +
				"    transactionHash: " + toIndentedString(transactionHash) + "\n" +
				"}";
		return sb;
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}