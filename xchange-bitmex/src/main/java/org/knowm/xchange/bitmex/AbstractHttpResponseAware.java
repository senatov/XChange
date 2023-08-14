package org.knowm.xchange.bitmex;

import si.mazi.rescu.HttpResponseAware;

import java.util.List;
import java.util.Map;

/**
 * @author Nikita Belenkiy on 02/07/2018.
 */
public abstract class AbstractHttpResponseAware implements HttpResponseAware {

	private Map<String, List<String>> headers;

	@Override
	public void setResponseHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	@Override
	public Map<String, List<String>> getResponseHeaders() {
		return this.headers;
	}
}