package org.knowm.xchange.kraken.dto.marketdata;

import java.util.Map;

public class KrakenAssetPairs {

	private final Map<String, KrakenAssetPair> assetPairMap;

	/**
	 * Constructor
	 */
	public KrakenAssetPairs(Map<String, KrakenAssetPair> assetPairMap) {
		this.assetPairMap = assetPairMap;
	}

	public Map<String, KrakenAssetPair> getAssetPairMap() {
		return assetPairMap;
	}
}