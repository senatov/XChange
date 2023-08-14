package org.knowm.xchange.kraken.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.account.KrakenLedger;
import org.knowm.xchange.kraken.dto.account.results.KrakenLedgerResult.KrakenLedgers;

import java.util.Map;

public class KrakenLedgerResult extends KrakenResult<KrakenLedgers> {

	/**
	 * Constructor
	 */
	public KrakenLedgerResult(
			@JsonProperty("result") KrakenLedgers result, @JsonProperty("error") String[] error) {
		super(result, error);
	}

	public static class KrakenLedgers {

		private final Map<String, KrakenLedger> ledgerMap;
		private final int count;

		/**
		 * Constructor
		 */
		public KrakenLedgers(Map<String, KrakenLedger> ledgerMap) {
			this.ledgerMap = ledgerMap;
			this.count = ledgerMap.size();
		}

		/**
		 * Constructor
		 */
		public KrakenLedgers(
				@JsonProperty("ledger") Map<String, KrakenLedger> ledgerMap,
				@JsonProperty("count") int count) {
			this.ledgerMap = ledgerMap;
			this.count = count;
		}

		public Map<String, KrakenLedger> getLedgerMap() {
			return ledgerMap;
		}

		public int getCount() {
			return count;
		}

		@Override
		public String toString() {
			return "KrakenLedgers [ledgerMap=" + ledgerMap + ", count=" + count + "]";
		}
	}
}