package org.knowm.xchange.okcoin.v3.dto.trade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum FutureSide {
	short_side("short"),
	long_side("long");

	private final String value;

	@JsonCreator
	public static FutureSide forValue(String value) {
		if (value == null) {
			return null;
		}
		return Stream.of(FutureSide.values())
				.filter(s -> value.equals(s.value))
				.findAny()
				.orElseThrow(() -> new RuntimeException("Invalid future side: " + value));
	}

	@JsonValue
	@Override
	public String toString() {
		return value;
	}
}