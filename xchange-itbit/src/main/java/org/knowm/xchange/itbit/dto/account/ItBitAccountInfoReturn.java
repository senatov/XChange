package org.knowm.xchange.itbit.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class ItBitAccountInfoReturn {

	private final String id;
	private final String userId;
	private final String name;
	private final ItBitAccountBalance[] balances;

	public ItBitAccountInfoReturn(
			@JsonProperty("id") String id,
			@JsonProperty("userId") String userId,
			@JsonProperty("name") String name,
			@JsonProperty("balances") ItBitAccountBalance[] balances) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.balances = balances;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public ItBitAccountBalance[] getBalances() {
		return balances;
	}

	@Override
	public String toString() {
		String builder = "ItBitAccountInfoReturn [id=" +
				id +
				", userId=" +
				userId +
				", name=" +
				name +
				", balances=" +
				Arrays.toString(balances) +
				"]";
		return builder;
	}
}