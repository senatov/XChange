package org.knowm.xchange.coinfloor.dto.trade;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinfloorMarketOrderResponseTest {
	@Test
	public void unmarshalTest() throws IOException {
		InputStream is =
				getClass()
						.getResourceAsStream(
								"/org/knowm/xchange/coinfloor/dto/trade/example-place-market-order.json");
		ObjectMapper mapper = new ObjectMapper();
		CoinfloorMarketOrderResponse response =
				mapper.readValue(is, CoinfloorMarketOrderResponse.class);
		assertThat(response.getRemaining()).isEqualTo("0.0000");
	}
}