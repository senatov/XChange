package org.knowm.xchange.oer;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

import java.math.BigDecimal;

/**
 * Various adapters for converting from OER DTOs to XChange DTOs
 */
public final class OERAdapters {

	/**
	 * private Constructor
	 */
	private OERAdapters() {
	}

	public static Ticker adaptTicker(CurrencyPair currencyPair, Double exchangeRate) {
		BigDecimal last = BigDecimal.valueOf(exchangeRate);
		return new Ticker.Builder().currencyPair(currencyPair).last(last).timestamp(null).build();
	}
}