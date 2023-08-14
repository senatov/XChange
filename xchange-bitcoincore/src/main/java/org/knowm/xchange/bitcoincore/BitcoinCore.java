package org.knowm.xchange.bitcoincore;

import org.knowm.xchange.bitcoincore.dto.BitcoinCoreException;
import org.knowm.xchange.bitcoincore.dto.account.BitcoinCoreBalanceRequest;
import org.knowm.xchange.bitcoincore.dto.account.BitcoinCoreBalanceResponse;
import org.knowm.xchange.bitcoincore.dto.account.BitcoinCoreUnconfirmedBalanceRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BitcoinCore {

	@POST
	@Path("")
	BitcoinCoreBalanceResponse getBalance(BitcoinCoreBalanceRequest request)
			throws IOException, BitcoinCoreException;

	@POST
	@Path("")
	BitcoinCoreBalanceResponse getUnconfirmedBalance(BitcoinCoreUnconfirmedBalanceRequest request)
			throws IOException, BitcoinCoreException;
}