package org.knowm.xchange.kraken.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.trade.KrakenOpenPosition;

import java.util.Map;

public class KrakenOpenPositionsResult extends KrakenResult<Map<String, KrakenOpenPosition>> {

	/**
	 * Constructor
	 */
	public KrakenOpenPositionsResult(
			@JsonProperty("result") Map<String, KrakenOpenPosition> result,
			@JsonProperty("error") String[] error) {
		super(result, error);
	}
}