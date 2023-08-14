package org.knowm.xchange.poloniex.dto.trade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.poloniex.dto.marketdata.PoloniexPublicTrade;
import si.mazi.rescu.ExceptionalReturnContentException;

import java.util.List;
import java.util.Map;

public class PoloniexMoveResponse {

	private final boolean success;

	private final Long orderNumber;

	private final Map<String, List<PoloniexPublicTrade>> resultingTrades;

	@JsonCreator
	public PoloniexMoveResponse(
			@JsonProperty("success") Boolean success,
			@JsonProperty("orderNumber") Long orderNumber,
			@JsonProperty("resultingTrades") Map<String, List<PoloniexPublicTrade>> resultingTrades) {
		if (orderNumber == null) {
			throw new ExceptionalReturnContentException("No trade data in response");
		}
		this.success = success;
		this.orderNumber = orderNumber;
		this.resultingTrades = resultingTrades;
	}

	public boolean success() {
		return success;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public Map<String, List<PoloniexPublicTrade>> getPoloniexPublicTrades() {
		return resultingTrades;
	}
}