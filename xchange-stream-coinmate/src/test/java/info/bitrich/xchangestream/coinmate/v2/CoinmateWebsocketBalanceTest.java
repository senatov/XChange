package info.bitrich.xchangestream.coinmate.v2;

import com.fasterxml.jackson.core.type.TypeReference;
import info.bitrich.xchangestream.coinmate.v2.dto.CoinmateWebsocketBalance;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoinmateWebsocketBalanceTest {

	@Test
	public void coinmateWebsocketOpenOrdersTest() throws IOException {
		String message =
				StreamingObjectMapperHelper.getObjectMapper()
						.readTree(this.getClass().getResource("/balance.json").openStream())
						.get("payload")
						.toString();
		Map<String, CoinmateWebsocketBalance> balanceMap =
				StreamingObjectMapperHelper.getObjectMapper()
						.readValue(message, new TypeReference<Map<String, CoinmateWebsocketBalance>>() {
						});
		assertThat(balanceMap).isNotNull();
		assertThat(balanceMap.size()).isEqualTo(8);
		assertThat(balanceMap.get("BTC").getBalance()).isEqualTo(BigDecimal.valueOf(2.445));
		assertThat(balanceMap.get("BTC").getReserved()).isEqualTo(BigDecimal.valueOf(1.222));
		assertThat(balanceMap.get("EUR").getBalance()).isEqualTo(BigDecimal.valueOf(10000));
		assertThat(balanceMap.get("EUR").getReserved()).isEqualTo(BigDecimal.valueOf(5000));
	}
}