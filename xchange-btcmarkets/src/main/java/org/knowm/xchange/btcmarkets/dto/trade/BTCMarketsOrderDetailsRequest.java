package org.knowm.xchange.btcmarkets.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class BTCMarketsOrderDetailsRequest {
	@JsonProperty
	private List<Long> orderIds;

	public BTCMarketsOrderDetailsRequest(List<Long> orderIds) {
		this.orderIds = orderIds;
	}

	public List<Long> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Long> orderIds) {
		this.orderIds = orderIds;
	}

	@Override
	public int hashCode() {
		return orderIds != null ? orderIds.hashCode() : 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BTCMarketsOrderDetailsRequest that = (BTCMarketsOrderDetailsRequest) o;
		return Objects.equals(orderIds, that.orderIds);
	}
}