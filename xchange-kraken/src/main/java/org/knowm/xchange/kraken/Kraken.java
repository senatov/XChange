package org.knowm.xchange.kraken;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenAssetPairsResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenAssetsResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenDepthResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenOHLCResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenPublicTradesResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenServerTimeResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenSpreadsResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenTickerResult;

/**
 * @author Benedikt Bünz
 */
@Path("0")
@Produces(MediaType.APPLICATION_JSON)
public interface Kraken {

	@GET
	@Path("public/Ticker")
	KrakenTickerResult getTicker(@QueryParam("pair") String currencyPairs);

	@GET
	@Path("public/OHLC")
	KrakenOHLCResult getOHLC(
			@QueryParam("pair") String currencyPairs,
			@QueryParam("interval") Integer interval,
			@QueryParam("since") Long since);

	@GET
	@Path("public/Depth")
	KrakenDepthResult getDepth(
			@QueryParam("pair") String currencyPair, @QueryParam("count") long count);

	@GET
	@Path("public/Trades")
	KrakenPublicTradesResult getTrades(@QueryParam("pair") String currencyPair);

	@GET
	@Path("public/Trades")
	KrakenPublicTradesResult getTrades(
			@QueryParam("pair") String currencyPair, @QueryParam("since") Long since);

	@GET
	@Path("public/Spread")
	KrakenSpreadsResult getSpread(
			@QueryParam("pair") String currencyPair, @QueryParam("since") Long since);

	@GET
	@Path("public/Assets")
	KrakenAssetsResult getAssets(
			@FormParam("aclass") String assetClass, @FormParam("asset") String assets);

	@GET
	@Path("public/AssetPairs")
	KrakenAssetPairsResult getAssetPairs(@FormParam("pair") String assetPairs);

	@GET
	@Path("public/Time")
	KrakenServerTimeResult getServerTime();
}