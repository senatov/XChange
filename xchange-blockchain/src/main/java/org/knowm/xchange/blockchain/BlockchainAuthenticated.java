package org.knowm.xchange.blockchain;

import org.knowm.xchange.blockchain.dto.BlockchainException;
import org.knowm.xchange.blockchain.dto.account.BlockchainAccountInformation;
import org.knowm.xchange.blockchain.dto.account.BlockchainDeposit;
import org.knowm.xchange.blockchain.dto.account.BlockchainDeposits;
import org.knowm.xchange.blockchain.dto.account.BlockchainFees;
import org.knowm.xchange.blockchain.dto.account.BlockchainWithdrawal;
import org.knowm.xchange.blockchain.dto.trade.BlockchainOrder;
import org.knowm.xchange.blockchain.params.BlockchainWithdrawalParams;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @see <a href="https://api.blockchain.info/v3/#/">Swagger</a>
 */
@Path("v3/exchange")
@Produces(MediaType.APPLICATION_JSON)
public interface BlockchainAuthenticated extends Blockchain {

	/**
	 * Receive current account balances
	 *
	 * @return This returns a map where the key {@link String} represents the primary data and the value which is an
	 * instance of type {@link BlockchainAccountInformation} is a list of account balances
	 */
	@Path("/accounts")
	@GET
	Map<String, List<BlockchainAccountInformation>> getAccountInformation();

	/**
	 * Request a withdrawal
	 * Call GET /whitelist first to retrieve the ID of the beneficiary.
	 *
	 * @return the withdrawal object created
	 */
	@Path("/withdrawals")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	BlockchainWithdrawal postWithdrawFunds(BlockchainWithdrawalParams blockchainWithdrawalRequest) throws IOException, BlockchainException;

	/**
	 * Get a list of withdrawals
	 *
	 * @return list of withdrawals object
	 */
	@Path("/withdrawals")
	@GET
	List<BlockchainWithdrawal> getWithdrawFunds(@QueryParam("from") Long startTime,
	                                            @QueryParam("to") Long endTime);

	/**
	 * Get a deposit address. Currently, only crypto currencies are supported
	 *
	 * @return the deposit object according to the currency
	 */
	@Path("/deposits/{symbol}")
	@POST
	BlockchainDeposit getDepositAddress(@PathParam("symbol") @FormParam("symbol") String symbol) throws IOException, BlockchainException;

	/**
	 * Get current fee level
	 *
	 * @return the fees of all currency pairs
	 */
	@Path("/fees")
	@GET
	BlockchainFees getFees();

	/**
	 * Get a list of deposits
	 *
	 * @return list of deposits object
	 */
	@Path("/deposits")
	@GET
	List<BlockchainDeposits> depositHistory(@QueryParam("from") Long startTime,
	                                        @QueryParam("to") Long endTime);

	/**
	 * Get a list orders
	 *
	 * @return live and historic orders, defaulting to live orders. Returns at most 100 results, use timestamp to
	 * paginate for further results
	 */
	@Path("/orders")
	@GET
	List<BlockchainOrder> getOrders();

	/**
	 * Get a list orders by symbol
	 *
	 * @return live and historic orders, defaulting to live orders. Returns at most 100 results, use timestamp to
	 * paginate for further results
	 */
	@Path("/orders")
	@GET
	List<BlockchainOrder> getOrdersBySymbol(@QueryParam("symbol") String symbol) throws IOException, BlockchainException;

	/**
	 * Get a specific order
	 *
	 * @return the order according to the orderId, 404 if not found
	 */
	@Path("/orders/{orderId}")
	@GET
	BlockchainOrder getOrder(@PathParam("orderId") String orderId) throws IOException, BlockchainException;

	/**
	 * Add an order
	 *
	 * @return a new order according to the provided parameters
	 */
	@Path("/orders")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	BlockchainOrder postOrder(BlockchainOrder blockchainOrder);

	/**
	 * Delete a specific order
	 *
	 * @return status 200 if it was successfully removed or 400 if there was an error
	 */
	@Path("/orders/{orderId}")
	@DELETE
	Void cancelOrder(@PathParam("orderId") String orderId) throws IOException, BlockchainException;

	/**
	 * Delete all open orders (of a symbol, if specified)
	 *
	 * @return status 200 if it was successfully removed or 400 if there was an error
	 */
	@Path("/orders")
	@DELETE
	Void cancelAllOrders(@QueryParam("symbol") String symbol) throws IOException, BlockchainException;

	/**
	 * Get a list of filled orders
	 *
	 * @return filled orders, including partial fills. Returns at most 100 results, use timestamp to paginate for
	 * further results
	 */
	@Path("/trades")
	@GET
	List<BlockchainOrder> getTrades(@QueryParam("symbol") String symbol,
	                                @QueryParam("from") Long startTime,
	                                @QueryParam("to") Long endTime,
	                                @QueryParam("limit") Integer limit) throws IOException, BlockchainException;

}