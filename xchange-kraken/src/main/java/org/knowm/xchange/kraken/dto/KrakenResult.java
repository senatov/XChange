package org.knowm.xchange.kraken.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author Raphael Voellmy
 */
@Getter
public class KrakenResult<V> {

	private final V result;
	private final String[] error;

	/**
	 * Constructor
	 */
	@JsonCreator
	public KrakenResult(@JsonProperty("return") V result, @JsonProperty("error") String[] error) {
		this.result = result;
		this.error = error;
	}

	@Override
	public String toString() {
		return String.format(
				"KrakenResult[%s: %s]",
				isSuccess() ? "OK" : "error", isSuccess() ? result.toString() : Arrays.toString(error));
	}

	public boolean isSuccess() {
		return error.length == 0;
	}
}