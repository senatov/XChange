package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class OpenOrdersReq {

	private String address;
	private String market;

	/**
	 * (address string) - Address to return open orders associated with
	 */
	public OpenOrdersReq address(String address) {
		this.address = address;
		return this;
	}

	@ApiModelProperty("(address string) - Address to return open orders associated with")
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 *
	 */
	public OpenOrdersReq market(String market) {
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

	@Override
	public int hashCode() {
		return Objects.hash(address, market);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OpenOrdersReq openOrdersReq = (OpenOrdersReq) o;
		return Objects.equals(address, openOrdersReq.address)
				&& Objects.equals(market, openOrdersReq.market);
	}

	@Override
	public String toString() {
		String sb = "class OpenOrdersReq {\n" +
				"    address: " + toIndentedString(address) + "\n" +
				"    market: " + toIndentedString(market) + "\n" +
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