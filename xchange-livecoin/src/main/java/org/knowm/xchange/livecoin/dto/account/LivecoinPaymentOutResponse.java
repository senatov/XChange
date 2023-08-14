package org.knowm.xchange.livecoin.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.livecoin.dto.LivecoinBaseResponse;

import java.util.Map;

/**
 * @author walec51
 */
public class LivecoinPaymentOutResponse extends LivecoinBaseResponse {

	private final Map data;

	public LivecoinPaymentOutResponse(
			@JsonProperty("success") Boolean success, @JsonProperty("data") Map data) {
		super(success);
		this.data = data;
	}

	public Map getData() {
		return data;
	}
}