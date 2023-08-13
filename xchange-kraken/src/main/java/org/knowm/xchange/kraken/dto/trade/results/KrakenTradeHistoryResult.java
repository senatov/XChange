package org.knowm.xchange.kraken.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.trade.KrakenTrade;
import org.knowm.xchange.kraken.dto.trade.results.KrakenTradeHistoryResult.KrakenTradeHistory;

import java.util.Map;

public class KrakenTradeHistoryResult extends KrakenResult<KrakenTradeHistory> {

	/**
	 * Constructor
	 */
	public KrakenTradeHistoryResult(
			@JsonProperty("result") KrakenTradeHistory result, @JsonProperty("error") String[] error) {
		super(result, error);
	}

	public static class KrakenTradeHistory {

		private final Map<String, KrakenTrade> orders;
		@Getter
		private final int count;

		/**
		 * Constructor
		 */
		public KrakenTradeHistory(
				@JsonProperty("trades") Map<String, KrakenTrade> orders, @JsonProperty("count") int count) {
			this.orders = orders;
			this.count = count;
		}

		public Map<String, KrakenTrade> getTrades() {
			return orders;
		}

	}
}