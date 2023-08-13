package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenSpreadsResult;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class KrakenSpreadsJSONTest {

	@Test
	public void testUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenSpreadsJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/marketdata/example-spreads-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenSpreadsResult krakenResult = mapper.readValue(is, KrakenSpreadsResult.class);
		KrakenSpreads spreads = krakenResult.getResult();
		assertThat(spreads.last()).isEqualTo(1391837200);
		KrakenSpread spread = spreads.spreads().get(0);
		assertThat(spread.ask()).isEqualTo("720.00000");
		assertThat(spread.bid()).isEqualTo("709.17169");
		assertThat(spread.time()).isEqualTo(1391836639);
	}
}