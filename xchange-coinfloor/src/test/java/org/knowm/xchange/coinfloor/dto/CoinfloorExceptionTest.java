package org.knowm.xchange.coinfloor.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import si.mazi.rescu.serialization.jackson.DefaultJacksonObjectMapperFactory;
import si.mazi.rescu.serialization.jackson.JacksonObjectMapperFactory;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinfloorExceptionTest {
	@Test
	public void unmarshalTest() throws IOException {
		JacksonObjectMapperFactory factory = new DefaultJacksonObjectMapperFactory();
		ObjectMapper mapper = factory.createObjectMapper();
		InputStream is =
				getClass()
						.getResourceAsStream(
								"/org/knowm/xchange/coinfloor/dto/trade/example-order-entry-reject.json");
		CoinfloorException exception = mapper.readValue(is, CoinfloorException.class);
		assertThat(exception.getMessage()).startsWith("You have insufficient funds.");
		assertThat(exception.getErrorCode()).isEqualTo(4);
	}
}