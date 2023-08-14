package org.knowm.xchange.idex.dto;

import java.util.Objects;

public class Return24VolumeResponse {

	@Override
	public int hashCode() {
		return Objects.hash();
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Return24VolumeResponse return24VolumeResponse = (Return24VolumeResponse) o;
		return true;
	}

	@Override
	public String toString() {
		String sb = "class Return24VolumeResponse {\n" +
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