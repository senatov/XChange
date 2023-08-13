package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record KrakenVolumeFee(BigDecimal fee, BigDecimal minFee, BigDecimal maxFee, BigDecimal nextFee,
                              BigDecimal nextVolume, BigDecimal tierVolume) {

	/**
	 * Constructor
	 */
	public KrakenVolumeFee(
			@JsonProperty("fee") BigDecimal fee,
			@JsonProperty("minfee") BigDecimal minFee,
			@JsonProperty("maxfee") BigDecimal maxFee,
			@JsonProperty("nextfee") BigDecimal nextFee,
			@JsonProperty("nextvolume") BigDecimal nextVolume,
			@JsonProperty("tiervolume") BigDecimal tierVolume) {
		this.fee = fee;
		this.minFee = minFee;
		this.maxFee = maxFee;
		this.nextFee = nextFee;
		this.nextVolume = nextVolume;
		this.tierVolume = tierVolume;
	}


	@Override
	public String toString() {
		return "KrakenVolumeFee [fee="
				+ fee
				+ ", minFee="
				+ minFee
				+ ", maxFee="
				+ maxFee
				+ ", nextFee="
				+ nextFee
				+ ", nextVolume="
				+ nextVolume
				+ ", tierVolume="
				+ tierVolume
				+ "]";
	}
}