package org.knowm.xchange.btcmarkets.dto.v3;

import org.junit.Test;
import org.knowm.xchange.btcmarkets.dto.v3.trade.BTCMarketsPlaceOrderResponse;
import org.knowm.xchange.btcmarkets.service.BTCMarketsTestSupport;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class BTCMarketsDtoTestV3 extends BTCMarketsTestSupport {

	@Test
	public void shouldParsePlaceOrderResponse() throws IOException {
		// when
		final BTCMarketsPlaceOrderResponse response = parse(BTCMarketsPlaceOrderResponse.class, "v3");
		// then
		assertThat(response.orderId).isEqualTo("7524");
	}
}