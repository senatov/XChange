package org.knowm.xchange.kraken.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class KrakenOrderResponse {

	private final KrakenOrderResponseDescription description;
	private final List<String> transactionIds;

	/**
	 * Constructor
	 */
	public KrakenOrderResponse(
			@JsonProperty("descr") KrakenOrderResponseDescription description,
			@JsonProperty("txid") List<String> transactionId) {
		this.description = description;
		this.transactionIds = transactionId;
	}

	@Override
	public String toString() {
		return "KrakenOrderResponse [description="
				+ description
				+ ", transactionId="
				+ transactionIds
				+ "]";
	}

	public record KrakenOrderResponseDescription(String orderDescription, String closeDescription) {

		/**
		 * Constructor
		 */
		public KrakenOrderResponseDescription(
				@JsonProperty("order") String orderDescription,
				@JsonProperty("close") String closeDescription) {
			this.orderDescription = orderDescription;
			this.closeDescription = closeDescription;
		}


		@Override
		public String toString() {
			return "KrakenOrderResponseDescription [orderDescription="
					+ orderDescription
					+ ", closeDescription="
					+ closeDescription
					+ "]";
		}
	}
}