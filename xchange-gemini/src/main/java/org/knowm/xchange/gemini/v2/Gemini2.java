package org.knowm.xchange.gemini.v2;


import org.knowm.xchange.gemini.v1.dto.GeminiException;
import org.knowm.xchange.gemini.v2.dto.marketdata.GeminiCandle;
import org.knowm.xchange.gemini.v2.dto.marketdata.GeminiTicker2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("v2")
@Produces(MediaType.APPLICATION_JSON)
public interface Gemini2 {
	@GET
	@Path("candles/{symbol}/{time_frame}")
	GeminiCandle[] getCandles(
			@PathParam("symbol") String symbol, @PathParam("time_frame") String time_frame)
			throws IOException, GeminiException;

	@GET
	@Path("ticker/{symbol}")
	GeminiTicker2 getTicker(@PathParam("symbol") String symbol) throws IOException, GeminiException;
}