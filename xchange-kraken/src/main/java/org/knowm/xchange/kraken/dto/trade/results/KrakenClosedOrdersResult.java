package org.knowm.xchange.kraken.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.trade.KrakenOrder;
import org.knowm.xchange.kraken.dto.trade.results.KrakenClosedOrdersResult.KrakenClosedOrders;

import java.util.Map;

public class KrakenClosedOrdersResult extends KrakenResult<KrakenClosedOrders> {

	/**
	 * Constructor
	 */
	public KrakenClosedOrdersResult(
			@JsonProperty("result") KrakenClosedOrders result, @JsonProperty("error") String[] error) {
		super(result, error);
	}

	public record KrakenClosedOrders(Map<String, KrakenOrder> orders) {

		public KrakenClosedOrders(@JsonProperty("closed") Map<String, KrakenOrder> orders) {
			this.orders = orders;
		}


	}
}