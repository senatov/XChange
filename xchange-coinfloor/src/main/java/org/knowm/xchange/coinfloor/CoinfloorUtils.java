package org.knowm.xchange.coinfloor;

import org.apache.commons.lang3.time.FastDateFormat;
import org.knowm.xchange.exceptions.ExchangeException;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public final class CoinfloorUtils {

	private static final FastDateFormat DATE_FORMAT =
			FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("UTC"));

	private CoinfloorUtils() {
	}

	public static Date parseDate(final String date) {
		try {
			return DATE_FORMAT.parse(date);
		} catch (final ParseException e) {
			throw new ExchangeException("Illegal date/time format: " + date, e);
		}
	}
}