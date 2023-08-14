package org.knowm.xchange.kraken.dto.marketdata;

import java.util.Map;

public class KrakenAssets {

	private final Map<String, KrakenAsset> assetInfoMap;

	/**
	 * Constructor
	 */
	public KrakenAssets(Map<String, KrakenAsset> assetInfoMap) {
		this.assetInfoMap = assetInfoMap;
	}

	public Map<String, KrakenAsset> getAssetPairMap() {
		return assetInfoMap;
	}

	@Override
	public String toString() {
		return "KrakenAssets [assetInfoMap=" + assetInfoMap + "]";
	}
}