package org.knowm.xchange.bitmex;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpResponseAware;
import si.mazi.rescu.HttpStatusException;
import si.mazi.rescu.HttpStatusExceptionSupport;

import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class BitmexException extends HttpStatusExceptionSupport
		implements HttpStatusException, HttpResponseAware {

	@JsonProperty("error")
	private Error error;

	private Map<String, List<String>> headers;
	private int httpStatusCode;

	public BitmexException(@JsonProperty("error") Error error) {
		this.error = error;
	}

	public String getErrorName() {
		return error.name;
	}

	@Override
	public String getMessage() {
		return (error.message == null ? error.name : error.message)
				+ " (HTTP:  "
				+ httpStatusCode
				+ ").";
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	static class Error {
		@JsonProperty("message")
		private String message;

		@JsonProperty("name")
		private String name;

		public Error(@JsonProperty("message") String message, @JsonProperty("name") String name) {
			this.message = message;
			this.name = name;
		}
	}	@Override
	public void setResponseHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	@Override
	public Map<String, List<String>> getResponseHeaders() {
		return headers;
	}


}