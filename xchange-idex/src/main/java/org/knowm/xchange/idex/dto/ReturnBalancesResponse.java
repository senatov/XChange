package org.knowm.xchange.idex.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ReturnBalancesResponse extends java.util.HashMap<String, BigDecimal> {

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReturnBalancesResponse returnBalancesResponse = (ReturnBalancesResponse) o;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash();
	}

	@Override
	public String toString() {
		String sb = "class ReturnBalancesResponse {\n" +
				"    " + toIndentedString(super.toString()) + "\n" +
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