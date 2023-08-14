package org.knowm.xchange.latoken.dto.exchangeinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LatokenRateLimit {

	private final String endpoint;
	private final String timePeriod;
	private final long requestLimit;

	/**
	 * C'tor
	 */
	public LatokenRateLimit(
			@JsonProperty("endpoint") String endpoint,
			@JsonProperty("timePeriod") String timePeriod,
			@JsonProperty("requestLimit") long requestLimit) {
		this.endpoint = endpoint;
		this.timePeriod = timePeriod;
		this.requestLimit = requestLimit;
	}

	/**
	 * API endpoint with method type
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * Time limit of requests
	 */
	public String getTimePeriod() {
		return timePeriod;
	}

	/**
	 * Amount of requests allowed in time period
	 */
	public long getRequestLimit() {
		return requestLimit;
	}

	@Override
	public String toString() {
		return "LatokenRateLimit [endpoint = "
				+ endpoint
				+ ", timePeriod = "
				+ timePeriod
				+ ", requestLimit = "
				+ requestLimit
				+ "]";
	}
}