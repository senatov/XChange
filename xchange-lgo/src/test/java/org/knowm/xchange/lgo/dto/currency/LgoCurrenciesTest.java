package org.knowm.xchange.lgo.dto.currency;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.lgo.dto.product.LgoProductsTest;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LgoCurrenciesTest {

	@Test
	public void itCanReadJson() throws IOException {
		InputStream is =
				LgoProductsTest.class.getResourceAsStream(
						"/org/knowm/xchange/lgo/currency/example-currencies-data.json");
		ObjectMapper mapper = new ObjectMapper();
		LgoCurrencies currencies = mapper.readValue(is, LgoCurrencies.class);
		assertThat(currencies.getCurrencies()).hasSize(2);
		assertThat(currencies.getCurrencies().get(0))
				.isEqualToComparingFieldByField(new LgoCurrency("Bitcoin", "BTC", 8));
		assertThat(currencies.getCurrencies().get(1))
				.isEqualToComparingFieldByField(new LgoCurrency("United States Dollar", "USD", 4));
	}
}