package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.utils.jackson.Rfc1123DateDeserializer;

import java.util.Date;

public record KrakenServerTime(long unixTime, Date rfc1123Time) {

	/**
	 * Constructor
	 */
	public KrakenServerTime(
			@JsonProperty("unixtime") long unixTime,
			@JsonProperty("rfc1123") @JsonDeserialize(using = Rfc1123DateDeserializer.class)
			Date rfc1123Time) {
		this.unixTime = unixTime;
		this.rfc1123Time = rfc1123Time;
	}


	@Override
	public String toString() {
		return "KrakenServerTime [unixTime=" + unixTime + ", rfc1123Time=" + rfc1123Time + "]";
	}
}