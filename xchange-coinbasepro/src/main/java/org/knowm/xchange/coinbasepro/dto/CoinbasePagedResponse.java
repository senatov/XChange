package org.knowm.xchange.coinbasepro.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import si.mazi.rescu.HttpResponseAware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CoinbasePagedResponse<T> extends ArrayList<T> implements HttpResponseAware {

	@JsonIgnore
	private Map<String, List<String>> headers;

	public String getBefore() {
		return headers.getOrDefault("Cb-Before", Collections.emptyList()).stream()
				.findFirst()
				.orElse(null);
	}	@Override
	public void setResponseHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public String getAfter() {
		return headers.getOrDefault("Cb-After", Collections.emptyList()).stream()
				.findFirst()
				.orElse(null);
	}	@Override
	public Map<String, List<String>> getResponseHeaders() {
		return headers;
	}




}