package org.knowm.xchange.bitcoinde.v4.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Date;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BitcoindeMaintenance {

	String message;
	Date start;
	Date end;

	@JsonCreator
	public BitcoindeMaintenance(
			@JsonProperty("message") String message,
			@JsonProperty("start") Date start,
			@JsonProperty("end") Date end) {
		this.message = message;
		this.start = start;
		this.end = end;
	}
}