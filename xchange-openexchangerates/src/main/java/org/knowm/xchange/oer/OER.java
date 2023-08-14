package org.knowm.xchange.oer;

import org.knowm.xchange.oer.dto.marketdata.OERTickers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author timmolter
 */
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public interface OER {

	@GET
	@Path("latest.json")
	OERTickers getTickers(
			@QueryParam("app_id") String appId,
			@QueryParam("base") String base,
			@QueryParam("symbols") String symbols)
			throws IOException;
}