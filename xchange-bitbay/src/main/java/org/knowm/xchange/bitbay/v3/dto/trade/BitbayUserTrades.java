package org.knowm.xchange.bitbay.v3.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitbay.v3.dto.BitbayBaseResponse;

import java.util.List;

/**
 * @author walec51
 */
public class BitbayUserTrades extends BitbayBaseResponse {

	private final List<BitbayUserTrade> items;
	private final String nextPageCursor;

	public BitbayUserTrades(
			@JsonProperty("items") List<BitbayUserTrade> items,
			@JsonProperty("nextPageCursor") String nextPageCursor,
			@JsonProperty("status") String status,
			@JsonProperty("errors") List<String> errors) {
		super(status, errors);
		this.items = items;
		this.nextPageCursor = nextPageCursor;
	}

	public List<BitbayUserTrade> getItems() {
		return items;
	}

	public String getNextPageCursor() {
		return nextPageCursor;
	}
}