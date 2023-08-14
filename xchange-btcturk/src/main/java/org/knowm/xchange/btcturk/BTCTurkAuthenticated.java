package org.knowm.xchange.btcturk;

import org.knowm.xchange.btcturk.dto.account.BTCTurkAccountBalance;
import org.knowm.xchange.btcturk.dto.account.BTCTurkDepositRequestResult;
import org.knowm.xchange.btcturk.dto.account.BTCTurkUserTransactions;
import org.knowm.xchange.btcturk.dto.account.BTCTurkWithdrawalRequestInfo;
import org.knowm.xchange.btcturk.dto.trade.BTCTurkCancelOrderResult;
import org.knowm.xchange.btcturk.dto.trade.BTCTurkExchangeResult;
import org.knowm.xchange.btcturk.dto.trade.BTCTurkOpenOrders;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * @author mertguner
 */
@Path("api/")
@Produces(MediaType.APPLICATION_JSON)
public interface BTCTurkAuthenticated extends BTCTurk {

	/**
	 * Get the authenticated account's balance.
	 *
	 * @return An object of type AccountBalance. Null if account balance cannot be retreived.
	 * @see BTCTurkAccountBalance
	 */
	@GET
	@Path("balance/")
	BTCTurkAccountBalance getBalance(
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Get the authenticated account's latest transactions. Includes all balance changes. Buys, sells,
	 * deposits, withdrawals and fees.
	 *
	 * @return A list of object type UserTransOutput. Null if user tranasctions cannot be retreived.
	 * @see BTCTurkUserTransactions
	 */
	@GET
	@Path("userTransactions/")
	List<BTCTurkUserTransactions> getUserTransactions(
			@QueryParam("offset") int offset,
			@QueryParam("limit") int limit,
			@QueryParam("sort") String sort,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Get all open orders of the user.
	 *
	 * @return Users open orders listed. Null if there was an error.
	 * @see BTCTurkOpenOrders
	 */
	@GET
	@Path("openOrders/")
	List<BTCTurkOpenOrders> getOpenOrders(
			@QueryParam("pairSymbol") String pairSymbol,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Submits given Order. Requires authentication.
	 *
	 * @return True if Order is submitted successfully, false if it was not.
	 * @see BTCTurkExchangeResult
	 */
	@POST
	@Path("exchange/")
	BTCTurkExchangeResult setOrder(
			@FormParam("Price") String Price,
			@FormParam("PricePrecision") String PricePrecision,
			@FormParam("Amount") String Amount,
			@FormParam("AmountPrecision") String AmountPrecision,
			@FormParam("OrderType") int OrderType,
			@FormParam("OrderMethod") int OrderMethod,
			@FormParam("PairSymbol") String PairSymbol,
			@FormParam("DenominatorPrecision") int DenominatorPrecision,
			@FormParam("Total") String Total,
			@FormParam("TotalPrecision") String TotalPrecision,
			@FormParam("TriggerPrice") String TriggerPrice,
			@FormParam("TriggerPricePrecision") String TriggerPricePrecision,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Cancels order with given OrderId
	 *
	 * @param id in BTCTurkCancelOrderRequest
	 * @return True if order was cancelled, false otherwise
	 * @see BTCTurkCancelOrderResult
	 */
	@POST
	@Path("cancelOrder/")
	BTCTurkCancelOrderResult setCancelOrder(
			@FormParam("id") String id,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Get the deposit money info
	 *
	 * @return The deposit money. Null if there was an error
	 * @see BTCTurkDepositRequestResult
	 * @deprecated by BtcTurk
	 */
	@GET
	@Path("DepositMoney/")
	BTCTurkDepositRequestResult getDepositRequest(
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Send the deposit money request, and return the deposit money request info.
	 *
	 * @param amount in BTCTurkDepositRequest
	 * @param amountPrecision in BTCTurkDepositRequest
	 * @return If a request is already, return the deposit money info. Null if there was an error
	 * @see BTCTurkDepositRequestResult
	 * @deprecated by BtcTurk
	 */
	@POST
	@Path("DepositMoney/")
	BTCTurkDepositRequestResult setDepositRequest(
			@FormParam("amount") String amount,
			@FormParam("amount_precision") String amountPrecision,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Cancel money requests Deposit with given RequestId.
	 *
	 * @return True if request was cancelled, false otherwise
	 * @see Boolean
	 * @deprecated by BtcTurk
	 */
	@DELETE
	@Path("DepositMoney/CancelOperation/")
	Boolean cancelDepositRequest(
			@QueryParam("balanceRequestId") String balanceRequestId,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Get the withdrawal money info
	 *
	 * @return The withdrawal money. Null if there was an error
	 * @see BTCTurkWithdrawalRequestInfo
	 * @deprecated by BtcTurk
	 */
	@GET
	@Path("WithdrawalMoney/")
	BTCTurkWithdrawalRequestInfo getWithdrawalRequest(
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Send the withdrawal money request, and return the withdrawal money request info.
	 *
	 * @param Iban in BTCTurkWithdrawalRequest
	 * @param FriendlyName in BTCTurkWithdrawalRequest
	 * @param FriendlyNameSave in BTCTurkWithdrawalRequest
	 * @param Amount in BTCTurkWithdrawalRequest
	 * @param AmountPrecision in BTCTurkWithdrawalRequest
	 * @param HasBalanceRequest in BTCTurkWithdrawalRequest
	 * @param BalanceRequestId in BTCTurkWithdrawalRequest
	 * @param BankId in BTCTurkWithdrawalRequest
	 * @param BankName in BTCTurkWithdrawalRequest
	 * @param FirstName in BTCTurkWithdrawalRequest
	 * @param LastName in BTCTurkWithdrawalRequest
	 * @return If a request is already, return the withdrawal money info. Null if there was an error
	 * @see BTCTurkWithdrawalRequestInfo
	 * @deprecated by BtcTurk
	 */
	@POST
	@Path("WithdrawalMoney/")
	BTCTurkWithdrawalRequestInfo setWithdrawalRequest(
			@FormParam("iban") String Iban,
			@FormParam("friendly_name") String FriendlyName,
			@FormParam("friendly_name_save") Boolean FriendlyNameSave,
			@FormParam("amount") String Amount,
			@FormParam("amount_precision") String AmountPrecision,
			@FormParam("has_balance_request") Boolean HasBalanceRequest,
			@FormParam("balance_request_id") String BalanceRequestId,
			@FormParam("bank_id") String BankId,
			@FormParam("bank_name") String BankName,
			@FormParam("first_name") String FirstName,
			@FormParam("last_name") String LastName,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;

	/**
	 * Cancel money requests Withdrawal with given RequestId
	 *
	 * @return True if request was cancelled, false otherwise
	 * @see Boolean
	 * @deprecated by BtcTurk
	 */
	@DELETE
	@Path("WithdrawalMoney/CancelOperation/")
	Boolean cancelWithdrawalRequest(
			@QueryParam("balanceRequestId") String balanceRequestId,
			@HeaderParam("X-PCK") String apiKey,
			@HeaderParam("X-Stamp") SynchronizedValueFactory<Long> stamp,
			@HeaderParam("X-Signature") ParamsDigest signature)
			throws IOException;
}