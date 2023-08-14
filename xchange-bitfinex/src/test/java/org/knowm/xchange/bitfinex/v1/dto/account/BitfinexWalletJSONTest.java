package org.knowm.xchange.bitfinex.v1.dto.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Test BTCEDepth JSON parsing
 */
public class BitfinexWalletJSONTest {

	@Test
	public void testUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				BitfinexWalletJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/bitfinex/v1/dto/account/example-account-info-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		BitfinexBalancesResponse readValue = mapper.readValue(is, BitfinexBalancesResponse.class);
		assertEquals(readValue.getAmount().toString(), new BigDecimal("8.53524686").toString());
	}
}