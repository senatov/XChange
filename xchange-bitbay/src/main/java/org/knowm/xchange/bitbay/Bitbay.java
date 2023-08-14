package org.knowm.xchange.bitbay;

import org.knowm.xchange.bitbay.dto.marketdata.BitbayOrderBook;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayTicker;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayTrade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author kpysniak
 */
@Path("/Public")
@Produces(MediaType.APPLICATION_JSON)
public interface Bitbay {

	/**
	 * @return Bitbay ticker
	 */
	@GET
	@Path("{currencyPair}/ticker.json")
	BitbayTicker getBitbayTicker(@PathParam("currencyPair") String currencyPair) throws IOException;

	@GET
	@Path("{currencyPair}/orderbook.json")
	BitbayOrderBook getBitbayOrderBook(@PathParam("currencyPair") String currencyPair)
			throws IOException;

	@GET
	@Path("{currencyPair}/trades.json")
	BitbayTrade[] getBitbayTrades(
			@PathParam("currencyPair") String currencyPair,
			@QueryParam("since") long sinceId,
			@QueryParam("sort") String sort,
			@QueryParam("limit") int limit)
			throws IOException;
}