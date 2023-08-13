package org.knowm.xchange.bybit;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.knowm.xchange.bybit.dto.BybitResult;
import org.knowm.xchange.bybit.dto.marketdata.BybitSymbol;
import org.knowm.xchange.bybit.dto.marketdata.BybitTicker;
import org.knowm.xchange.bybit.service.BybitException;

import java.io.IOException;
import java.util.List;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface Bybit {

	/**
	 *
	 */
	@GET
	@Path("/v2/public/tickers")
	BybitResult<List<BybitTicker>> getTicker24h(@QueryParam("symbol") String symbol) throws IOException, BybitException;

	/**
	 *
	 */
	@GET
	@Path("/v2/public/symbols")
	BybitResult<List<BybitSymbol>> getSymbols() throws IOException, BybitException;

}