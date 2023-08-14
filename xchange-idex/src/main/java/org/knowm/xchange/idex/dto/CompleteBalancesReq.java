package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class CompleteBalancesReq {

	private String address;

	/**
	 * (address string) - Address to query balances of
	 */
	public CompleteBalancesReq address(String address) {
		this.address = address;
		return this;
	}

	@ApiModelProperty("(address string) - Address to query balances of")
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CompleteBalancesReq completeBalancesReq = (CompleteBalancesReq) o;
		return Objects.equals(address, completeBalancesReq.address);
	}

	@Override
	public String toString() {
		String sb = "class CompleteBalancesReq {\n" +
				"    address: " + toIndentedString(address) + "\n" +
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