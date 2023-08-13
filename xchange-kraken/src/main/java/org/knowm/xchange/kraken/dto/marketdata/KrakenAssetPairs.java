package org.knowm.xchange.kraken.dto.marketdata;

import java.util.Map;

public record KrakenAssetPairs(Map<String, KrakenAssetPair> assetPairMap) {

	/**
	 * Constructor
	 */
	public KrakenAssetPairs {
	}


}