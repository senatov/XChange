package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenAssetsResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class KrakenAssetsJSONTest {

	@Test
	public void testUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenAssetsJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/marketdata/example-assets-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenAssetsResult krakenResult = mapper.readValue(is, KrakenAssetsResult.class);
		Map<String, KrakenAsset> assets = krakenResult.getResult();
		assertThat(assets).hasSize(27);
		KrakenAsset asset = assets.get("XXBT");
		assertThat(asset.altName()).isEqualTo("XBT");
		assertThat(asset.assetClass()).isEqualTo("currency");
		assertThat(asset.displayScale()).isEqualTo(5);
		assertThat(asset.scale()).isEqualTo(10);
	}
}