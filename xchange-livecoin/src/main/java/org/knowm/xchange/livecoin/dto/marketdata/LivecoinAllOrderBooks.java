package org.knowm.xchange.livecoin.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.livecoin.dto.LivecoinBaseResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author walec51
 */
public class LivecoinAllOrderBooks extends LivecoinBaseResponse {

	private final Map<String, LivecoinOrderBook> orderBooksByPair =
			new HashMap<String, LivecoinOrderBook>();

	public LivecoinAllOrderBooks(@JsonProperty("success") Boolean success) {
		super(success);
	}

	@JsonAnyGetter
	public Map<String, LivecoinOrderBook> getOrderBooksByPair() {
		return orderBooksByPair;
	}

	@JsonAnySetter
	public void setOrderBooksByPair(String pair, LivecoinOrderBook orderBook) {
		orderBooksByPair.put(pair, orderBook);
	}
}