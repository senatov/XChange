package org.knowm.xchange.upbit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.upbit.service.UpbitArrayOrMessageDeserializer;

/**
 * @author interwater
 */
@JsonDeserialize(using = UpbitTickers.UpbitTickersDeserializer.class)
public class UpbitTickers {
	private final UpbitTicker[] tickers;

	public UpbitTickers(@JsonProperty() UpbitTicker[] tickers) {
		this.tickers = tickers;
	}

	public UpbitTicker[] getTickers() {
		return tickers;
	}

	static class UpbitTickersDeserializer
			extends UpbitArrayOrMessageDeserializer<UpbitTicker, UpbitTickers> {
		public UpbitTickersDeserializer() {
			super(UpbitTicker.class, UpbitTickers.class);
		}
	}
}