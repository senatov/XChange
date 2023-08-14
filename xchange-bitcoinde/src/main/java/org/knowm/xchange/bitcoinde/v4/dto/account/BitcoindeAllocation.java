package org.knowm.xchange.bitcoinde.v4.dto.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoindeAllocation {

	BigDecimal percent;
	BigDecimal maxEurVolume;
	BigDecimal eurVolumeOpenOrders;

	@JsonCreator
	public BitcoindeAllocation(
			@JsonProperty("percent") BigDecimal percent,
			@JsonProperty("max_eur_volume") BigDecimal maxEurVolume,
			@JsonProperty("eur_volume_open_orders") BigDecimal eurVolumeOpenOrders) {
		this.percent = percent;
		this.maxEurVolume = maxEurVolume;
		this.eurVolumeOpenOrders = eurVolumeOpenOrders;
	}
}