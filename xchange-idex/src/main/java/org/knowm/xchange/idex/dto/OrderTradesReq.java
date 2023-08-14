package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class OrderTradesReq {

	private String orderHash;

	/**
	 * (256-bit hex string) - The order hash to query for associated trades
	 */
	public OrderTradesReq orderHash(String orderHash) {
		this.orderHash = orderHash;
		return this;
	}

	@ApiModelProperty("(256-bit hex string) - The order hash to query for associated trades")
	@JsonProperty("orderHash")
	public String getOrderHash() {
		return orderHash;
	}

	public void setOrderHash(String orderHash) {
		this.orderHash = orderHash;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderHash);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OrderTradesReq orderTradesReq = (OrderTradesReq) o;
		return Objects.equals(orderHash, orderTradesReq.orderHash);
	}

	@Override
	public String toString() {
		String sb = "class OrderTradesReq {\n" +
				"    orderHash: " + toIndentedString(orderHash) + "\n" +
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