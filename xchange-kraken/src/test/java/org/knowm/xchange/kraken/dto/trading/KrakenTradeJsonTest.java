package org.knowm.xchange.kraken.dto.trading;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.kraken.dto.trade.KrakenOrder;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderDescription;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderResponse;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderStatus;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderType;
import org.knowm.xchange.kraken.dto.trade.KrakenTrade;
import org.knowm.xchange.kraken.dto.trade.KrakenType;
import org.knowm.xchange.kraken.dto.trade.results.KrakenCancelOrderResult;
import org.knowm.xchange.kraken.dto.trade.results.KrakenCancelOrderResult.KrakenCancelOrderResponse;
import org.knowm.xchange.kraken.dto.trade.results.KrakenOpenOrdersResult;
import org.knowm.xchange.kraken.dto.trade.results.KrakenOrderResult;
import org.knowm.xchange.kraken.dto.trade.results.KrakenTradeHistoryResult;
import org.knowm.xchange.kraken.dto.trade.results.KrakenTradeHistoryResult.KrakenTradeHistory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

public class KrakenTradeJsonTest {

	@Test
	public void testOrderUnmarshall() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenTradeJsonTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/trading/example-openorders-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenOpenOrdersResult krakenResult = mapper.readValue(is, KrakenOpenOrdersResult.class);
		Entry<String, KrakenOrder> openOrderEntry =
				krakenResult.getResult().orders().entrySet().iterator().next();
		KrakenOrder order = openOrderEntry.getValue();
		// Verify that the example data was unmarshalled correctly
		assertThat(openOrderEntry.getKey()).isEqualTo("O767CW-TXHCL-FWZ5R2");
		assertThat(order.getOpenTimestamp()).isEqualTo(1499872460.2572);
		assertThat(order.getPrice()).isEqualTo("0.000000000");
		assertThat(order.getVolume()).isEqualTo("1000.00000000");
		assertThat(order.getVolumeExecuted()).isEqualTo("0.00000000");
		assertThat(order.getStatus()).isEqualTo(KrakenOrderStatus.OPEN);
		KrakenOrderDescription orderDescription = order.getOrderDescription();
		assertThat(orderDescription.assetPair()).isEqualTo("XRPXBT");
		assertThat(orderDescription.leverage()).isEqualTo("none");
		assertThat(orderDescription.orderDescription())
				.isEqualTo("buy 1000.00000000 XRPXBT @ limit 0.00001000");
		assertThat(orderDescription.orderType()).isEqualTo(KrakenOrderType.LIMIT);
		assertThat(orderDescription.type()).isEqualTo(KrakenType.BUY);
		assertThat(orderDescription.price()).isEqualTo("0.00001000");
		assertThat(orderDescription.secondaryPrice()).isEqualTo("0");
	}

	@Test
	public void testTradeHistoryUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenTradeJsonTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/trading/example-tradehistory-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenTradeHistoryResult krakenResult = mapper.readValue(is, KrakenTradeHistoryResult.class);
		KrakenTradeHistory krakenTradeHistory = krakenResult.getResult();
		Map<String, KrakenTrade> krakenTradeHistoryMap = krakenTradeHistory.getTrades();
		KrakenTrade trade = krakenTradeHistoryMap.get("TY5BYV-WJUQF-XPYEYD-1");
		assertThat(trade.assetPair()).isEqualTo("XLTCXXBT");
		assertThat(trade.price()).isEqualTo("32.07562");
		assertThat(trade.cost()).isEqualTo("16.03781");
		assertThat(trade.fee()).isEqualTo("0.03208");
		assertThat(trade.margin()).isEqualTo("0.00000");
		assertThat(trade.volume()).isEqualTo("0.50000000");
		assertThat(trade.orderTxId()).isEqualTo("ONRNOX-DVI4W-76DL6Q-1");
		assertThat(trade.unixTimestamp()).isEqualTo(1389071942.2089);
		assertThat(trade.type()).isEqualTo(KrakenType.SELL);
		assertThat(trade.orderType()).isEqualTo(KrakenOrderType.MARKET);
		assertThat(trade.miscellaneous()).isEqualTo("");
	}

	@Test
	public void testCancelOrderUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenTradeJsonTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/trading/example-cancelorder-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenCancelOrderResult krakenResult = mapper.readValue(is, KrakenCancelOrderResult.class);
		KrakenCancelOrderResponse cancelOrderResponse = krakenResult.getResult();
		assertThat(cancelOrderResponse.count()).isEqualTo(1);
		assertFalse(cancelOrderResponse.pending());
	}

	@Test
	public void testAddOrderResponseUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenTradeJsonTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/trading/example-addorder-response-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenOrderResult krakenResult = mapper.readValue(is, KrakenOrderResult.class);
		KrakenOrderResponse orderResponse = krakenResult.getResult();
		assertThat(orderResponse.getDescription().orderDescription())
				.isEqualTo("sell 0.01000000 XBTLTC @ limit 45.25000");
		assertThat(orderResponse.getTransactionIds().get(0)).isEqualTo("OWQJ5O-ZWYC7-5R7POQ");
	}
}