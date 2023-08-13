package org.knowm.xchange.kraken.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.trade.results.KrakenCancelOrderResult.KrakenCancelOrderResponse;

public class KrakenCancelOrderResult extends KrakenResult<KrakenCancelOrderResponse> {

	/**
	 * Constructor
	 */
	public KrakenCancelOrderResult(
			@JsonProperty("result") KrakenCancelOrderResponse result,
			@JsonProperty("error") String[] error) {
		super(result, error);
	}

	public record KrakenCancelOrderResponse(int count, boolean pending) {

		/**
		 * Constructor
		 */
		public KrakenCancelOrderResponse(
				@JsonProperty("count") int count, @JsonProperty("pending") boolean pending) {
			this.count = count;
			this.pending = pending;
		}


		@Override
		public String toString() {
			return "KrakenCancelOrderResponse [count=" + count + ", pending=" + pending + "]";
		}
	}
}