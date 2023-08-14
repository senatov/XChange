package org.knowm.xchange.bitstamp.dto.trade;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Transaction[] JSON parsing
 */
public class CancelAllOrdersJSONTest {

	@Test
	public void testUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				CancelOrderJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/bitstamp/dto/trade/example-cancel-all-orders.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		BitstampCancelAllOrdersResponse result =
				mapper.readValue(is, BitstampCancelAllOrdersResponse.class);
		assertThat(result.success).isTrue();
	}
}