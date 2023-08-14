package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class TradeReq {

	private String orderHash;
	private String amount;
	private String nonce;
	private String address;
	private java.math.BigInteger v;
	private String r;
	private String s;

	/**
	 * - This is the unsalted hash of the order you are filling. See raw in the example code given
	 * with in the section that describes the order API call. The orderHash property of an order can
	 * be retrieved from the API calls which return orders, but for higher security we recommend you
	 * derive the hash yourself from the order parameters.
	 */
	public TradeReq orderHash(String orderHash) {
		this.orderHash = orderHash;
		return this;
	}

	@ApiModelProperty(
			"- This is the unsalted hash of the order you are filling. See raw in the example code given with in the section that describes the order API call. The orderHash property of an order can be retrieved from the API calls which return orders, but for higher security we recommend you derive the hash yourself from the order parameters.")
	@JsonProperty("orderHash")
	public String getOrderHash() {
		return orderHash;
	}

	public void setOrderHash(String orderHash) {
		this.orderHash = orderHash;
	}

	/**
	 * - This is the amount of the order you are filling, the raw value not adjusted for precision
	 * IMPORTANT: THIS PROPERTY IS IN TERMS OF THE ORDER&#39;S amountBuy PROPERTY. This is NOT the
	 * amount of tokenSell you are receiving, but the amount of tokenBuy you are filling the order
	 * with. Do not trade unless you fully understand this idea. The amount of the token you will
	 * receive as a result of the trade is proportional to the ratio between amountSell and amountBuy
	 */
	public TradeReq amount(String amount) {
		this.amount = amount;
		return this;
	}

	@ApiModelProperty(
			"- This is the amount of the order you are filling, the raw value not adjusted for precision IMPORTANT: THIS PROPERTY IS IN TERMS OF THE ORDER'S amountBuy PROPERTY. This is NOT the amount of tokenSell you are receiving, but the amount of tokenBuy you are filling the order with. Do not trade unless you fully understand this idea. The amount of the token you will receive as a result of the trade is proportional to the ratio between amountSell and amountBuy")
	@JsonProperty("amount")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * - One time numeric value associated with the trade. Note: if filling multiple orders in one
	 * trade, every nonce in the list of trades must be greater than the one in the previous item
	 */
	public TradeReq nonce(String nonce) {
		this.nonce = nonce;
		return this;
	}

	@ApiModelProperty(
			"- One time numeric value associated with the trade. Note: if filling multiple orders in one trade, every nonce in the list of trades must be greater than the one in the previous item")
	@JsonProperty("nonce")
	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	/**
	 * - The address you are transacting from
	 */
	public TradeReq address(String address) {
		this.address = address;
		return this;
	}

	@ApiModelProperty("- The address you are transacting from")
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * - ...
	 */
	public TradeReq v(java.math.BigInteger v) {
		this.v = v;
		return this;
	}

	@ApiModelProperty("- ...")
	@JsonProperty("v")
	public java.math.BigInteger getV() {
		return v;
	}

	public void setV(java.math.BigInteger v) {
		this.v = v;
	}

	/**
	 * - 0x
	 */
	public TradeReq r(String r) {
		this.r = r;
		return this;
	}

	@ApiModelProperty("- 0x")
	@JsonProperty("r")
	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	/**
	 * - v, r, and s refer to the values produced by signing the message
	 */
	public TradeReq s(String s) {
		this.s = s;
		return this;
	}

	@ApiModelProperty("- v, r, and s refer to the values produced by signing the message")
	@JsonProperty("s")
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderHash, amount, nonce, address, v, r, s);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TradeReq tradeReq = (TradeReq) o;
		return Objects.equals(orderHash, tradeReq.orderHash)
				&& Objects.equals(amount, tradeReq.amount)
				&& Objects.equals(nonce, tradeReq.nonce)
				&& Objects.equals(address, tradeReq.address)
				&& Objects.equals(v, tradeReq.v)
				&& Objects.equals(r, tradeReq.r)
				&& Objects.equals(s, tradeReq.s);
	}

	@Override
	public String toString() {
		String sb = "class TradeReq {\n" +
				"    orderHash: " + toIndentedString(orderHash) + "\n" +
				"    amount: " + toIndentedString(amount) + "\n" +
				"    nonce: " + toIndentedString(nonce) + "\n" +
				"    address: " + toIndentedString(address) + "\n" +
				"    v: " + toIndentedString(v) + "\n" +
				"    r: " + toIndentedString(r) + "\n" +
				"    s: " + toIndentedString(s) + "\n" +
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