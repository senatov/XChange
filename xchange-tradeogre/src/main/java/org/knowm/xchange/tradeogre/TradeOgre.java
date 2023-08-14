package org.knowm.xchange.tradeogre;

import org.knowm.xchange.tradeogre.dto.marketdata.TradeOgreOrderBook;
import org.knowm.xchange.tradeogre.dto.marketdata.TradeOgreTicker;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("")
public interface TradeOgre {

	@GET
	@Path("ticker/{market}")
	TradeOgreTicker getTicker(@PathParam("market") String market) throws IOException;

	@GET
	@Path("markets")
	List<Map<String, TradeOgreTicker>> getTickers() throws IOException;

	@GET
	@Path("orders/{market}")
	TradeOgreOrderBook getOrderBook(@PathParam("market") String market) throws IOException;
}