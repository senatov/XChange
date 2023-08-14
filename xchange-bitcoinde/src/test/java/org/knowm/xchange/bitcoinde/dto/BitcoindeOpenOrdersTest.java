package org.knowm.xchange.bitcoinde.dto;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.bitcoinde.trade.BitcoindeMyOpenOrdersWrapper;
import org.knowm.xchange.bitcoinde.trade.BitcoindeMyOrder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BitcoindeOpenOrdersTest {

	@Test
	public void testBitcoindeOpenOrders()
			throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				BitcoindeOpenOrdersTest.class.getResourceAsStream(
						"/org/knowm/xchange/bitcoinde/dto/orders.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		BitcoindeMyOpenOrdersWrapper bitcoindeOpenOrdersWrapper =
				mapper.readValue(is, BitcoindeMyOpenOrdersWrapper.class);
		//    System.out.println("bitcoindeTradesWrapper = " + bitcoindeOpenOrdersWrapper);
		// Make sure trade values are correct
		List<BitcoindeMyOrder> orders = bitcoindeOpenOrdersWrapper.getOrders();
		BitcoindeMyOrder order = orders.get(0);
		assertEquals(1, orders.size());
		assertEquals("VNSP86", order.getOrderId());
	}
}