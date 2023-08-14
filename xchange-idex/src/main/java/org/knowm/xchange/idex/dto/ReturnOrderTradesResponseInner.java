package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class ReturnOrderTradesResponseInner {

	private String date = "";
	private String amount = "";
	private IdexBuySell type;
	private String total = "";
	private String price = "";
	private String uuid = "";
	private String transactionHash = "";

	/**
	 *
	 */
	public ReturnOrderTradesResponseInner date(String date) {
		this.date = date;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 *
	 */
	public ReturnOrderTradesResponseInner amount(String amount) {
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
	public ReturnOrderTradesResponseInner type(IdexBuySell type) {
		this.type = type;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("type")
	public IdexBuySell getType() {
		return type;
	}

	public void setType(IdexBuySell type) {
		this.type = type;
	}

	/**
	 *
	 */
	public ReturnOrderTradesResponseInner total(String total) {
		this.total = total;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("total")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 *
	 */
	public ReturnOrderTradesResponseInner price(String price) {
		this.price = price;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 *
	 */
	public ReturnOrderTradesResponseInner uuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("uuid")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 *
	 */
	public ReturnOrderTradesResponseInner transactionHash(String transactionHash) {
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
		return Objects.hash(date, amount, type, total, price, uuid, transactionHash);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReturnOrderTradesResponseInner returnOrderTradesResponseInner =
				(ReturnOrderTradesResponseInner) o;
		return Objects.equals(date, returnOrderTradesResponseInner.date)
				&& Objects.equals(amount, returnOrderTradesResponseInner.amount)
				&& Objects.equals(type, returnOrderTradesResponseInner.type)
				&& Objects.equals(total, returnOrderTradesResponseInner.total)
				&& Objects.equals(price, returnOrderTradesResponseInner.price)
				&& Objects.equals(uuid, returnOrderTradesResponseInner.uuid)
				&& Objects.equals(transactionHash, returnOrderTradesResponseInner.transactionHash);
	}

	@Override
	public String toString() {
		String sb = "class ReturnOrderTradesResponseInner {\n" +
				"    date: " + toIndentedString(date) + "\n" +
				"    amount: " + toIndentedString(amount) + "\n" +
				"    type: " + toIndentedString(type) + "\n" +
				"    total: " + toIndentedString(total) + "\n" +
				"    price: " + toIndentedString(price) + "\n" +
				"    uuid: " + toIndentedString(uuid) + "\n" +
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