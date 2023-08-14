package org.knowm.xchange.gateio.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.gateio.GateioAdapters;
import org.knowm.xchange.gateio.dto.account.GateioDepositsWithdrawals;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.DefaultWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.MoneroWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.RippleWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GateioAccountService extends GateioAccountServiceRaw implements AccountService {

	/**
	 * Constructor
	 */
	public GateioAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return new AccountInfo(GateioAdapters.adaptWallet(super.getGateioAccountInfo()));
	}

	@Override
	public String withdrawFunds(Currency currency, BigDecimal amount, String address)
			throws IOException {
		return withdraw(currency.getCurrencyCode(), amount, address);
	}

	@Override
	public String withdrawFunds(WithdrawFundsParams params) throws IOException {
		if (params instanceof RippleWithdrawFundsParams xrpParams) {
			return withdraw(
					xrpParams.getCurrency(),
					xrpParams.getAmount(),
					xrpParams.getAddress(),
					xrpParams.getTag());
		} else if (params instanceof MoneroWithdrawFundsParams xmrParams) {
			return withdraw(
					xmrParams.getCurrency(),
					xmrParams.getAmount(),
					xmrParams.getAddress(),
					xmrParams.getPaymentId());
		} else if (params instanceof DefaultWithdrawFundsParams defaultParams) {
			return withdrawFunds(
					defaultParams.getCurrency(), defaultParams.getAmount(), defaultParams.getAddress());
		}
		throw new IllegalStateException("Don't know how to withdraw: " + params);
	}

	@Override
	public String requestDepositAddress(Currency currency, String... args) throws IOException {
		return super.getGateioDepositAddress(currency).getBaseAddress();
	}

	@Override
	public TradeHistoryParams createFundingHistoryParams() {
		throw new NotAvailableFromExchangeException();
	}

	@Override
	public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {
		Date start = null;
		Date end = null;
		if (params instanceof TradeHistoryParamsTimeSpan timeSpan) {
			start = timeSpan.getStartTime();
			end = timeSpan.getEndTime();
		}
		GateioDepositsWithdrawals depositsWithdrawals = getDepositsWithdrawals(start, end);
		return GateioAdapters.adaptDepositsWithdrawals(depositsWithdrawals);
	}
}