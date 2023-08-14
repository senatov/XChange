package org.knowm.xchange.bitmex;

import si.mazi.rescu.HttpResponseAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nikita Belenkiy on 03/07/2018.
 */
@SuppressWarnings("serial")
public class HttpResponseAwareList<E> extends ArrayList<E> implements HttpResponseAware {
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