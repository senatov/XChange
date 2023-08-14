package org.knowm.xchange.paymium.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.paymium.PaymiumAdapters;
import org.knowm.xchange.paymium.dto.account.PaymiumOrder;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.TradeHistoryParamLimit;
import org.knowm.xchange.service.trade.params.TradeHistoryParamOffset;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymiumAccountService extends PaymiumAccountServiceRaw implements AccountService {

	/**
	 * Constructor
	 */
	public PaymiumAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return new AccountInfo(PaymiumAdapters.adaptWallet(getPaymiumBalances()));
	}

	@Override
	public TradeHistoryParams createFundingHistoryParams() {
		return new PaymiumHistoryParams();
	}

	@Override
	public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {
		List<FundingRecord> res = new ArrayList<>();
		Long offset = null;
		Integer limit = null;
		if (params instanceof TradeHistoryParamOffset historyParamOffset) {
			offset = historyParamOffset.getOffset();
		}
		if (params instanceof TradeHistoryParamLimit historyParamLimit) {
			limit = historyParamLimit.getLimit();
		}
		List<PaymiumOrder> orders = getPaymiumFundingOrders(offset, limit);
		for (PaymiumOrder order : orders) {
			FundingRecord.Type funding = null;
			switch (order.getType()) {
				case "WireDeposit":
				case "BitcoinDeposit":
					funding = FundingRecord.Type.DEPOSIT;
					break;
				case "Transfer":
					funding = FundingRecord.Type.WITHDRAWAL;
					break;
			}
			res.add(
					new FundingRecord(
							order.getBitcoinAddress(),
							order.getUpdatedAt(),
							Currency.getInstance(order.getCurrency()),
							order.getAmount(),
							String.valueOf(order.getUuid()),
							order.getUuid(),
							funding,
							FundingRecord.Status.COMPLETE,
							null,
							null,
							null));
		}
		return res;
	}
}