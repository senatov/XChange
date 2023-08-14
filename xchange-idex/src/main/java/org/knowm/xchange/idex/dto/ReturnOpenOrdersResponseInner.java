package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class ReturnOpenOrdersResponseInner {

	private java.math.BigInteger timestamp;
	private String orderHash = "";
	private String market = "";
	private IdexBuySell type;
	private java.math.BigInteger orderNumber;
	private ReturnOpenOrdersResponseInnerParams params;
	private String price = "";
	private String amount = "";
	private String total = "";

	/**
	 *
	 */
	public ReturnOpenOrdersResponseInner timestamp(java.math.BigInteger timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("timestamp")
	public java.math.BigInteger getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.math.BigInteger timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 *
	 */
	public ReturnOpenOrdersResponseInner orderHash(String orderHash) {
		this.orderHash = orderHash;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("orderHash")
	public String getOrderHash() {
		return orderHash;
	}

	public void setOrderHash(String orderHash) {
		this.orderHash = orderHash;
	}

	/**
	 *
	 */
	public ReturnOpenOrdersResponseInner market(String market) {
		this.market = market;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("market")
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 *
	 */
	public ReturnOpenOrdersResponseInner type(IdexBuySell type) {
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
	public ReturnOpenOrdersResponseInner orderNumber(java.math.BigInteger orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("orderNumber")
	public java.math.BigInteger getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(java.math.BigInteger orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 *
	 */
	public ReturnOpenOrdersResponseInner params(ReturnOpenOrdersResponseInnerParams params) {
		this.params = params;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("params")
	public ReturnOpenOrdersResponseInnerParams getParams() {
		return params;
	}

	public void setParams(ReturnOpenOrdersResponseInnerParams params) {
		this.params = params;
	}

	/**
	 *
	 */
	public ReturnOpenOrdersResponseInner price(String price) {
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
	public ReturnOpenOrdersResponseInner amount(String amount) {
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
	public ReturnOpenOrdersResponseInner total(String total) {
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

	@Override
	public int hashCode() {
		return Objects.hash(
				timestamp, orderHash, market, type, orderNumber, params, price, amount, total);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReturnOpenOrdersResponseInner returnOpenOrdersResponseInner = (ReturnOpenOrdersResponseInner) o;
		return Objects.equals(timestamp, returnOpenOrdersResponseInner.timestamp)
				&& Objects.equals(orderHash, returnOpenOrdersResponseInner.orderHash)
				&& Objects.equals(market, returnOpenOrdersResponseInner.market)
				&& Objects.equals(type, returnOpenOrdersResponseInner.type)
				&& Objects.equals(orderNumber, returnOpenOrdersResponseInner.orderNumber)
				&& Objects.equals(params, returnOpenOrdersResponseInner.params)
				&& Objects.equals(price, returnOpenOrdersResponseInner.price)
				&& Objects.equals(amount, returnOpenOrdersResponseInner.amount)
				&& Objects.equals(total, returnOpenOrdersResponseInner.total);
	}

	@Override
	public String toString() {
		String sb = "class ReturnOpenOrdersResponseInner {\n" +
				"    timestamp: " + toIndentedString(timestamp) + "\n" +
				"    orderHash: " + toIndentedString(orderHash) + "\n" +
				"    market: " + toIndentedString(market) + "\n" +
				"    type: " + toIndentedString(type) + "\n" +
				"    orderNumber: " + toIndentedString(orderNumber) + "\n" +
				"    params: " + toIndentedString(params) + "\n" +
				"    price: " + toIndentedString(price) + "\n" +
				"    amount: " + toIndentedString(amount) + "\n" +
				"    total: " + toIndentedString(total) + "\n" +
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