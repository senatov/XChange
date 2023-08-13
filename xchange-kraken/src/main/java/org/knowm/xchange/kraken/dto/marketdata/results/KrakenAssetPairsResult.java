package org.knowm.xchange.kraken.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.marketdata.KrakenAssetPair;

import java.util.Map;

public class KrakenAssetPairsResult extends KrakenResult<Map<String, KrakenAssetPair>> {

	/**
	 * Constructor
	 */
	public KrakenAssetPairsResult(
			@JsonProperty("result") Map<String, KrakenAssetPair> result,
			@JsonProperty("error") String[] error) {
		super(result, error);
	}
}