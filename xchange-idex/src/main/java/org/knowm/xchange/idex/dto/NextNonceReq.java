package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class NextNonceReq {

	private String address;

	/**
	 * (address string) - The address to query for the next nonce to use
	 */
	public NextNonceReq address(String address) {
		this.address = address;
		return this;
	}

	@ApiModelProperty("(address string) - The address to query for the next nonce to use")
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
		NextNonceReq nextNonceReq = (NextNonceReq) o;
		return Objects.equals(address, nextNonceReq.address);
	}

	@Override
	public String toString() {
		String sb = "class NextNonceReq {\n" +
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