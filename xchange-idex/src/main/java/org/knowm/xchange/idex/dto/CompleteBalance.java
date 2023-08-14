package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class CompleteBalance {

	private BigDecimal available;
	private BigDecimal onOrders;

	/**
	 *
	 */
	public CompleteBalance available(BigDecimal available) {
		this.available = available;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("available")
	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	/**
	 *
	 */
	public CompleteBalance onOrders(BigDecimal onOrders) {
		this.onOrders = onOrders;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("onOrders")
	public BigDecimal getOnOrders() {
		return onOrders;
	}

	public void setOnOrders(BigDecimal onOrders) {
		this.onOrders = onOrders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(available, onOrders);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CompleteBalance completeBalance = (CompleteBalance) o;
		return Objects.equals(available, completeBalance.available)
				&& Objects.equals(onOrders, completeBalance.onOrders);
	}

	@Override
	public String toString() {
		String sb = "class CompleteBalance {\n" +
				"    available: " + toIndentedString(available) + "\n" +
				"    onOrders: " + toIndentedString(onOrders) + "\n" +
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