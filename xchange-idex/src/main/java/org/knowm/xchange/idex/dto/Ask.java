package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class Ask {

	private String price = "";
	private String amount = "";
	private String total = "";
	private String orderHash = "";
	private AskParams params;

	/**
	 *
	 */
	public Ask price(String price) {
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
	public Ask amount(String amount) {
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
	public Ask total(String total) {
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
	public Ask orderHash(String orderHash) {
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
	public Ask params(AskParams params) {
		this.params = params;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("params")
	public AskParams getParams() {
		return params;
	}

	public void setParams(AskParams params) {
		this.params = params;
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, amount, total, orderHash, params);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Ask ask = (Ask) o;
		return Objects.equals(price, ask.price)
				&& Objects.equals(amount, ask.amount)
				&& Objects.equals(total, ask.total)
				&& Objects.equals(orderHash, ask.orderHash)
				&& Objects.equals(params, ask.params);
	}

	@Override
	public String toString() {
		String sb = "class Ask {\n" +
				"    price: " + toIndentedString(price) + "\n" +
				"    amount: " + toIndentedString(amount) + "\n" +
				"    total: " + toIndentedString(total) + "\n" +
				"    orderHash: " + toIndentedString(orderHash) + "\n" +
				"    params: " + toIndentedString(params) + "\n" +
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