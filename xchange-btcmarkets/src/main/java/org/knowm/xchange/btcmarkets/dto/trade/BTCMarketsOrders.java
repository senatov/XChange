package org.knowm.xchange.btcmarkets.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btcmarkets.dto.BTCMarketsBaseResponse;

import java.util.List;

public class BTCMarketsOrders extends BTCMarketsBaseResponse {
	private final List<BTCMarketsOrder> orders;

	public BTCMarketsOrders(
			@JsonProperty("success") Boolean success,
			@JsonProperty("errorMessage") String errorMessage,
			@JsonProperty("errorCode") Integer errorCode,
			@JsonProperty("orders") List<BTCMarketsOrder> orders) {
		super(success, errorMessage, errorCode);
		this.orders = orders;
	}

	public List<BTCMarketsOrder> getOrders() {
		return orders;
	}
}