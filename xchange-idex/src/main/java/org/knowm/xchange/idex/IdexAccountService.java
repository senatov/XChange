package org.knowm.xchange.idex;

import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.FundingRecord.Status;
import org.knowm.xchange.dto.account.FundingRecord.Type;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.idex.dto.CompleteBalancesReq;
import org.knowm.xchange.idex.dto.DepositsWithdrawalsReq;
import org.knowm.xchange.idex.dto.ReturnCompleteBalancesResponse;
import org.knowm.xchange.idex.dto.ReturnDepositsWithdrawalsResponse;
import org.knowm.xchange.idex.dto.WithdrawReq;
import org.knowm.xchange.idex.dto.WithdrawResponse;
import org.knowm.xchange.idex.service.ReturnCompleteBalancesApi;
import org.knowm.xchange.idex.service.ReturnDepositsWithdrawalsApi;
import org.knowm.xchange.idex.service.WithdrawApi;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.knowm.xchange.idex.IdexExchange.Companion.safeParse;

public class IdexAccountService extends BaseExchangeService implements AccountService {

	private final ReturnCompleteBalancesApi returnCompleteBalancesApi;
	private final ReturnDepositsWithdrawalsApi returnDepositsWithdrawalsApi;
	private final WithdrawApi withdrawApi;
	private final String apiKey;

	public IdexAccountService(IdexExchange idexExchange) {
		super(idexExchange);
		returnCompleteBalancesApi =
				ExchangeRestProxyBuilder.forInterface(
								ReturnCompleteBalancesApi.class, idexExchange.getExchangeSpecification())
						.build();
		returnDepositsWithdrawalsApi =
				ExchangeRestProxyBuilder.forInterface(
								ReturnDepositsWithdrawalsApi.class, idexExchange.getExchangeSpecification())
						.build();
		withdrawApi =
				ExchangeRestProxyBuilder.forInterface(
								WithdrawApi.class, idexExchange.getExchangeSpecification())
						.build();
		apiKey = exchange.getExchangeSpecification().getApiKey();
	}

	@Override
	public AccountInfo getAccountInfo() {
		AccountInfo ret = null;
		try {
			String s = apiKey.substring(0, 6) + "…";
			ReturnCompleteBalancesResponse returnBalancesPost;
			ret = null;
			returnBalancesPost =
					returnCompleteBalancesApi.completeBalances(new CompleteBalancesReq().address(apiKey));
			ret =
					new AccountInfo(
							Wallet.Builder.from(
											returnBalancesPost.entrySet().stream()
													.map(
															entry ->
																	new Balance(
																			new Currency(entry.getKey()),
																			null,
																			entry.getValue().getAvailable(),
																			entry.getValue().getOnOrders()))
													.collect(Collectors.toList()))
									.id(s)
									.build());

		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		return ret;
	}

	@Override
	public String withdrawFunds(Currency currency, BigDecimal amount, String address) {
		throw new org.knowm.xchange.exceptions.NotAvailableFromExchangeException();
	}

	@Override
	public String withdrawFunds(WithdrawFundsParams w) {
		String ret = "error";
		if (w instanceof IdexWithdraw) {
			WithdrawResponse withdraw = null;
			try {
				withdraw = withdrawApi.withdraw((WithdrawReq) w);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ret = withdraw.toString();
		} else {
			throw new Error(
					"withdraw method requires "
							+ org.knowm.xchange.idex.IdexWithdraw.class.getCanonicalName());
		}
		return ret;
	}

	@Override
	public String requestDepositAddress(Currency currency, String... args) {
		return exchange.getExchangeSpecification().getApiKey();
	}

	@Override
	public TradeHistoryParams createFundingHistoryParams() {
		return new IdexDepositsWithdrawalsParams(apiKey);
	}

	@Override
	public List<FundingRecord> getFundingHistory(TradeHistoryParams params) {
		List<FundingRecord> ret = null;
		if (!(params instanceof IdexDepositsWithdrawalsParams)) {
			throw new Error(
					"getFundingHistory requires " + IdexDepositsWithdrawalsParams.class.getCanonicalName());
		} else {
			try {
				ret =
						mutableList(
								returnDepositsWithdrawalsApi.fundingHistory((DepositsWithdrawalsReq) params));
			} catch (Exception e) {
			}
		}
		return ret;
	}

	private List<FundingRecord> mutableList(
			ReturnDepositsWithdrawalsResponse returnDepositsWithdrawalsPost) {
		return Arrays.asList(
						returnDepositsWithdrawalsPost.getWithdrawals().stream()
								.map(
										fundingLedger ->
												new FundingRecord(
														exchange.getExchangeSpecification().getApiKey(),
														new Date(Long.parseLong(fundingLedger.getTimestamp()) * 1000),
														new Currency(fundingLedger.getCurrency()),
														safeParse(fundingLedger.getAmount()),
														fundingLedger.getTransactionHash(),
														fundingLedger.getDepositNumber(),
														Type.WITHDRAWAL,
														Status.resolveStatus(fundingLedger.getStatus()),
														BigDecimal.ZERO,
														BigDecimal.ZERO,
														""))
								.collect(Collectors.toList()),
						returnDepositsWithdrawalsPost.getDeposits().stream()
								.map(
										fundingLedger1 ->
												new FundingRecord(
														exchange.getExchangeSpecification().getApiKey(),
														new Date(Long.parseLong(fundingLedger1.getTimestamp()) * 1000),
														new Currency(fundingLedger1.getCurrency()),
														safeParse(fundingLedger1.getAmount()),
														fundingLedger1.getTransactionHash(),
														fundingLedger1.getDepositNumber(),
														Type.DEPOSIT,
														Status.resolveStatus(fundingLedger1.getStatus()),
														BigDecimal.ZERO,
														BigDecimal.ZERO,
														""))
								.collect(Collectors.toList()))
				.stream()
				.flatMap(List::stream)
				.sorted(Comparator.comparing(FundingRecord::getDate))
				.collect(Collectors.toList());
	}
}