package org.knowm.xchange.kraken.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.account.KrakenLedger;

import java.util.Map;

public class KrakenQueryLedgerResult extends KrakenResult<Map<String, KrakenLedger>> {

	/**
	 * Constructor
	 */
	public KrakenQueryLedgerResult(
			@JsonProperty("result") Map<String, KrakenLedger> result,
			@JsonProperty("error") String[] error) {
		super(result, error);
	}
}