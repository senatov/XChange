package org.knowm.xchange.lykke;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.lykke.dto.trade.LykkeFeeType;
import org.knowm.xchange.lykke.dto.trade.LykkeTradeHistory;
import org.knowm.xchange.lykke.dto.trade.LykkeTradeState;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LykkeTradeHistoryTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testUnmarshal() throws IOException {
		InputStream is =
				LykkeTradeHistoryTest.class.getResourceAsStream(
						"/org/knowm/xchange/lykke/example-lykkeTradeHistory.json");
		LykkeTradeHistory[] lykkeTradeHistory = mapper.readValue(is, LykkeTradeHistory[].class);
		assertThat(lykkeTradeHistory[0].getId()).isEqualTo("string");
		assertThat(lykkeTradeHistory[0].getDatetime()).isEqualTo("2018-07-19T19:21:12.411Z");
		assertThat(lykkeTradeHistory[0].getState()).isEqualTo(LykkeTradeState.InProgress);
		assertThat(lykkeTradeHistory[0].getAmount()).isEqualTo(0);
		assertThat(lykkeTradeHistory[0].getAsset()).isEqualTo("string");
		assertThat(lykkeTradeHistory[0].getAssetPair()).isEqualTo("string");
		assertThat(lykkeTradeHistory[0].getPrice()).isEqualTo(0);
		assertThat(lykkeTradeHistory[0].getFee().getAmount()).isEqualTo(0);
		assertThat(lykkeTradeHistory[0].getFee().getType()).isEqualTo(LykkeFeeType.Unknown);
	}
}