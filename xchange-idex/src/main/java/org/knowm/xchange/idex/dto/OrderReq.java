package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class OrderReq {

	private String tokenBuy;
	private String amountBuy;
	private String tokenSell;
	private String amountSell;
	private String address;
	private java.math.BigInteger nonce;
	private java.math.BigInteger expires;
	private java.math.BigInteger v;
	private String r;
	private String s;

	/**
	 * (address string) - The address of the token you will receive as a result of the trade
	 */
	public OrderReq tokenBuy(String tokenBuy) {
		this.tokenBuy = tokenBuy;
		return this;
	}

	@ApiModelProperty(
			"(address string) - The address of the token you will receive as a result of the trade")
	@JsonProperty("tokenBuy")
	public String getTokenBuy() {
		return tokenBuy;
	}

	public void setTokenBuy(String tokenBuy) {
		this.tokenBuy = tokenBuy;
	}

	/**
	 * (uint256) - The amount of the token you will receive when the order is fully filled
	 */
	public OrderReq amountBuy(String amountBuy) {
		this.amountBuy = amountBuy;
		return this;
	}

	@ApiModelProperty(
			"(uint256) - The amount of the token you will receive when the order is fully filled")
	@JsonProperty("amountBuy")
	public String getAmountBuy() {
		return amountBuy;
	}

	public void setAmountBuy(String amountBuy) {
		this.amountBuy = amountBuy;
	}

	/**
	 * (address string) - The address of the token you will lose as a result of the trade
	 */
	public OrderReq tokenSell(String tokenSell) {
		this.tokenSell = tokenSell;
		return this;
	}

	@ApiModelProperty(
			"(address string) - The address of the token you will lose as a result of the trade")
	@JsonProperty("tokenSell")
	public String getTokenSell() {
		return tokenSell;
	}

	public void setTokenSell(String tokenSell) {
		this.tokenSell = tokenSell;
	}

	/**
	 * (uint256) - The amount of the token you will give up when the order is fully filled
	 */
	public OrderReq amountSell(String amountSell) {
		this.amountSell = amountSell;
		return this;
	}

	@ApiModelProperty(
			"(uint256) - The amount of the token you will give up when the order is fully filled")
	@JsonProperty("amountSell")
	public String getAmountSell() {
		return amountSell;
	}

	public void setAmountSell(String amountSell) {
		this.amountSell = amountSell;
	}

	/**
	 * (address string) - The address you are posting the order from
	 */
	public OrderReq address(String address) {
		this.address = address;
		return this;
	}

	@ApiModelProperty("(address string) - The address you are posting the order from")
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * (uint256) - One time number associated with the limit order
	 */
	public OrderReq nonce(java.math.BigInteger nonce) {
		this.nonce = nonce;
		return this;
	}

	@ApiModelProperty("(uint256) - One time number associated with the limit order")
	@JsonProperty("nonce")
	public java.math.BigInteger getNonce() {
		return nonce;
	}

	public void setNonce(java.math.BigInteger nonce) {
		this.nonce = nonce;
	}

	/**
	 * (uint256) - DEPRECATED this property has no effect on your limit order but is still REQUIRED to
	 * submit a limit order as it is one of the parameters that is hashed. It must be a numeric type
	 */
	public OrderReq expires(java.math.BigInteger expires) {
		this.expires = expires;
		return this;
	}

	@ApiModelProperty(
			"(uint256) - DEPRECATED this property has no effect on your limit order but is still REQUIRED to submit a limit order as it is one of the parameters that is hashed. It must be a numeric type")
	@JsonProperty("expires")
	public java.math.BigInteger getExpires() {
		return expires;
	}

	public void setExpires(java.math.BigInteger expires) {
		this.expires = expires;
	}

	/**
	 *
	 */
	public OrderReq v(java.math.BigInteger v) {
		this.v = v;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("v")
	public java.math.BigInteger getV() {
		return v;
	}

	public void setV(java.math.BigInteger v) {
		this.v = v;
	}

	/**
	 *
	 */
	public OrderReq r(String r) {
		this.r = r;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("r")
	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	/**
	 *
	 */
	public OrderReq s(String s) {
		this.s = s;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("s")
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				tokenBuy, amountBuy, tokenSell, amountSell, address, nonce, expires, v, r, s);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OrderReq orderReq = (OrderReq) o;
		return Objects.equals(tokenBuy, orderReq.tokenBuy)
				&& Objects.equals(amountBuy, orderReq.amountBuy)
				&& Objects.equals(tokenSell, orderReq.tokenSell)
				&& Objects.equals(amountSell, orderReq.amountSell)
				&& Objects.equals(address, orderReq.address)
				&& Objects.equals(nonce, orderReq.nonce)
				&& Objects.equals(expires, orderReq.expires)
				&& Objects.equals(v, orderReq.v)
				&& Objects.equals(r, orderReq.r)
				&& Objects.equals(s, orderReq.s);
	}

	@Override
	public String toString() {
		String sb = "class OrderReq {\n" +
				"    tokenBuy: " + toIndentedString(tokenBuy) + "\n" +
				"    amountBuy: " + toIndentedString(amountBuy) + "\n" +
				"    tokenSell: " + toIndentedString(tokenSell) + "\n" +
				"    amountSell: " + toIndentedString(amountSell) + "\n" +
				"    address: " + toIndentedString(address) + "\n" +
				"    nonce: " + toIndentedString(nonce) + "\n" +
				"    expires: " + toIndentedString(expires) + "\n" +
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