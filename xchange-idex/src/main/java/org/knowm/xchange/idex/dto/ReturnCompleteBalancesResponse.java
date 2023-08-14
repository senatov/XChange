package org.knowm.xchange.idex.dto;

import java.util.Objects;

public class ReturnCompleteBalancesResponse extends java.util.HashMap<String, CompleteBalance> {

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReturnCompleteBalancesResponse returnCompleteBalancesResponse =
				(ReturnCompleteBalancesResponse) o;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash();
	}

	@Override
	public String toString() {
		String sb = "class ReturnCompleteBalancesResponse {\n" +
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