package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class IdexCurrMeta {

	private java.math.BigInteger decimals;
	private String address = "";
	private String name = "";

	/**
	 *
	 */
	public IdexCurrMeta decimals(java.math.BigInteger decimals) {
		this.decimals = decimals;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("decimals")
	public java.math.BigInteger getDecimals() {
		return decimals;
	}

	public void setDecimals(java.math.BigInteger decimals) {
		this.decimals = decimals;
	}

	/**
	 *
	 */
	public IdexCurrMeta address(String address) {
		this.address = address;
		return this;
	}

	@ApiModelProperty("")
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
	public IdexCurrMeta name(String name) {
		this.name = name;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(decimals, address, name);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		IdexCurrMeta idexCurrMeta = (IdexCurrMeta) o;
		return Objects.equals(decimals, idexCurrMeta.decimals)
				&& Objects.equals(address, idexCurrMeta.address)
				&& Objects.equals(name, idexCurrMeta.name);
	}

	@Override
	public String toString() {
		String sb = "class IdexCurrMeta {\n" +
				"    decimals: " + toIndentedString(decimals) + "\n" +
				"    address: " + toIndentedString(address) + "\n" +
				"    name: " + toIndentedString(name) + "\n" +
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