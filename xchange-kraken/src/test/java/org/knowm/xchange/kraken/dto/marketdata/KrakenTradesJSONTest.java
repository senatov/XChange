package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenPublicTradesResult;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderType;
import org.knowm.xchange.kraken.dto.trade.KrakenType;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class KrakenTradesJSONTest {

	@Test
	public void testUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenTradesJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/marketdata/example-trades-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenPublicTradesResult krakenTrades = mapper.readValue(is, KrakenPublicTradesResult.class);
		// Verify that the example data was unmarshalled correctly
		assertThat(krakenTrades.getResult().trades().get(0).price()).isEqualTo("1023.82219");
		assertThat(krakenTrades.getResult().trades().get(0).volume()).isEqualTo("0.00435995");
		assertThat(krakenTrades.getResult().trades().get(0).type()).isEqualTo(KrakenType.SELL);
		assertThat(krakenTrades.getResult().trades().get(0).orderType())
				.isEqualTo(KrakenOrderType.LIMIT);
		assertThat(krakenTrades.getResult().trades().get(1).time()).isEqualTo(1385579841.7876);
		long lastId = krakenTrades.getResult().last();
		assertThat(lastId).isEqualTo(1385579841881785998L);
	}
}