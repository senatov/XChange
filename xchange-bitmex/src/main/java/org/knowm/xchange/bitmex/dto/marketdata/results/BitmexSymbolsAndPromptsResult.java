package org.knowm.xchange.bitmex.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.AbstractHttpResponseAware;

import java.util.List;

public class BitmexSymbolsAndPromptsResult extends AbstractHttpResponseAware {

	private final List<String> intervals;
	private final List<String> symbols;

	/**
	 * Constructor
	 */
	@JsonCreator
	public BitmexSymbolsAndPromptsResult(
			@JsonProperty("intervals") List<String> intervals,
			@JsonProperty("symbols") List<String> symbols) {
		this.intervals = intervals;
		this.symbols = symbols;
	}

	public List<String> getIntervals() {
		return intervals;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	@Override
	public String toString() {
		return "BitmexSymbolsAndPromptsResult ["
				+ (intervals != null ? "intervals=" + intervals + ", " : "")
				+ (symbols != null ? "symbols=" + symbols : "")
				+ "]";
	}
}