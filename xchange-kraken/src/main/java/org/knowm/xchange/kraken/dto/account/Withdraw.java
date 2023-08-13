package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Withdraw(String refid) {
	/*
	 * refid = reference id *
	 */

	public Withdraw(@JsonProperty("refid") String refid) {
		this.refid = refid;
	}


	@Override
	public String toString() {
		return "Withdraw [refid=" + refid + "]";
	}
}