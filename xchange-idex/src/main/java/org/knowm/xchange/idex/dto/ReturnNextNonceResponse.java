package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class ReturnNextNonceResponse {

	private java.math.BigInteger nonce;

	/**
	 *
	 */
	public ReturnNextNonceResponse nonce(java.math.BigInteger nonce) {
		this.nonce = nonce;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("nonce")
	public java.math.BigInteger getNonce() {
		return nonce;
	}

	public void setNonce(java.math.BigInteger nonce) {
		this.nonce = nonce;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nonce);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReturnNextNonceResponse returnNextNonceResponse = (ReturnNextNonceResponse) o;
		return Objects.equals(nonce, returnNextNonceResponse.nonce);
	}

	@Override
	public String toString() {
		String sb = "class ReturnNextNonceResponse {\n" +
				"    nonce: " + toIndentedString(nonce) + "\n" +
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