package org.knowm.xchange.zaif;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.zaif.dto.marketdata.ZaifFullBook;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZaifAdaptersTest {

	@Test
	public void testFullBookAdapter() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				ZaifAdaptersTest.class.getResourceAsStream(
						"/org/knowm/xchange/zaif/marketdata/example-fullbook-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		ZaifFullBook zaifFullBook = mapper.readValue(is, ZaifFullBook.class);
		OrderBook orderBook = ZaifAdapters.adaptOrderBook(zaifFullBook, CurrencyPair.BTC_JPY);
		assertThat(orderBook.getBids().get(0).getInstrument()).isEqualTo(CurrencyPair.BTC_JPY);
		assertThat(orderBook.getBids().get(0).getLimitPrice()).isEqualTo("934920");
		assertThat(orderBook.getBids().get(0).getOriginalAmount()).isEqualTo("0.03");
		assertThat(orderBook.getAsks().get(0).getLimitPrice()).isEqualTo("935355");
		assertThat(orderBook.getAsks().get(0).getOriginalAmount()).isEqualTo("0.02");
	}
}