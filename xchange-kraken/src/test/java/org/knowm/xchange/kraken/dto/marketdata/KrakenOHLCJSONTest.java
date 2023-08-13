package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenOHLCResult;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class KrakenOHLCJSONTest {

	@Test
	public void testUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenOHLCJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/marketdata/example-ohlc-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenOHLCResult krakenOHLCs = mapper.readValue(is, KrakenOHLCResult.class);
		// Verify that the example data was unmarshalled correctly
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).time()).isEqualTo(1502402520L);
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).open())
				.isEqualTo(new BigDecimal("3449.992"));
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).high())
				.isEqualTo(new BigDecimal("3449.999"));
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).low())
				.isEqualTo(new BigDecimal("3449.992"));
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).close())
				.isEqualTo(new BigDecimal("3449.999"));
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).vwap())
				.isEqualTo(new BigDecimal("3449.997"));
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).volume())
				.isEqualTo(new BigDecimal("1.01200000"));
		assertThat(krakenOHLCs.getResult().OHLCs().get(0).count()).isEqualTo(Long.valueOf("7"));
		long lastId = krakenOHLCs.getResult().last();
		assertThat(lastId).isEqualTo(1502445600L);
	}
}