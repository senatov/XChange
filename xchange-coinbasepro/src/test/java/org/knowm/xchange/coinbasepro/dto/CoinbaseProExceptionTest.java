package org.knowm.xchange.coinbasepro.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import si.mazi.rescu.serialization.jackson.DefaultJacksonObjectMapperFactory;
import si.mazi.rescu.serialization.jackson.JacksonObjectMapperFactory;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinbaseProExceptionTest {
	@Test
	public void unmarshalTest() throws IOException {
		JacksonObjectMapperFactory factory = new DefaultJacksonObjectMapperFactory();
		ObjectMapper mapper = factory.createObjectMapper();
		InputStream is =
				getClass()
						.getResourceAsStream(
								"/org/knowm/xchange/coinbasepro/dto/trade/example-order-entry-reject.json");
		CoinbaseProException exception = mapper.readValue(is, CoinbaseProException.class);
		assertThat(exception.getMessage()).startsWith("Insufficient funds");
	}
}