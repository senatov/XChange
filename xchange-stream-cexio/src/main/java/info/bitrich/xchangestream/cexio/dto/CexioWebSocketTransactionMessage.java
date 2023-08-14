package info.bitrich.xchangestream.cexio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CexioWebSocketTransactionMessage {

	private final String e;
	private final CexioWebSocketTransaction data;

	public CexioWebSocketTransactionMessage(
			@JsonProperty("e") String e, @JsonProperty("data") CexioWebSocketTransaction data) {
		this.e = e;
		this.data = data;
	}

	public String getE() {
		return e;
	}

	public CexioWebSocketTransaction getData() {
		return data;
	}

	@Override
	public String toString() {
		String buffer = "{" + "e='" + e + '\'' +
				", data=" + data +
				'}';
		return buffer;
	}
}