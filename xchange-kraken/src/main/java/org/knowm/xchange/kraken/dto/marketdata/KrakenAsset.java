package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KrakenAsset(String altName, String assetClass, int scale, int displayScale) {

	/**
	 * Constructor
	 */
	public KrakenAsset(
			@JsonProperty("altname") String altName,
			@JsonProperty("aclass") String assetClass,
			@JsonProperty("decimals") int scale,
			@JsonProperty("display_decimals") int displayScale) {
		this.altName = altName;
		this.assetClass = assetClass;
		this.scale = scale;
		this.displayScale = displayScale;
	}


	@Override
	public String toString() {
		return "KrakenAssetInfo [altName="
				+ altName
				+ ", assetClass="
				+ assetClass
				+ ", scale="
				+ scale
				+ ", displayScale="
				+ displayScale
				+ "]";
	}
}