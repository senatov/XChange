package org.knowm.xchange.kraken.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Fee;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.kraken.KrakenAdapters;
import org.knowm.xchange.kraken.KrakenUtils;
import org.knowm.xchange.kraken.dto.account.KrakenDepositAddress;
import org.knowm.xchange.kraken.dto.account.KrakenLedger;
import org.knowm.xchange.kraken.dto.account.KrakenTradeBalanceInfo;
import org.knowm.xchange.kraken.dto.account.LedgerType;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamsTimeSpan;
import org.knowm.xchange.service.trade.params.DefaultWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.HistoryParamsFundingType;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencies;
import org.knowm.xchange.service.trade.params.TradeHistoryParamOffset;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static org.knowm.xchange.currency.Currency.ADA;
import static org.knowm.xchange.currency.Currency.ATOM;
import static org.knowm.xchange.currency.Currency.BCH;
import static org.knowm.xchange.currency.Currency.BTC;
import static org.knowm.xchange.currency.Currency.DASH;
import static org.knowm.xchange.currency.Currency.EOS;
import static org.knowm.xchange.currency.Currency.ETH;
import static org.knowm.xchange.currency.Currency.GNO;
import static org.knowm.xchange.currency.Currency.LTC;
import static org.knowm.xchange.currency.Currency.MLN;
import static org.knowm.xchange.currency.Currency.QTUM;
import static org.knowm.xchange.currency.Currency.REP;
import static org.knowm.xchange.currency.Currency.USD;
import static org.knowm.xchange.currency.Currency.XDG;
import static org.knowm.xchange.currency.Currency.XLM;
import static org.knowm.xchange.currency.Currency.XMR;
import static org.knowm.xchange.currency.Currency.XRP;
import static org.knowm.xchange.currency.Currency.XTZ;
import static org.knowm.xchange.currency.Currency.ZEC;

public class KrakenAccountService extends KrakenAccountServiceRaw implements AccountService {

	/**
	 * Constructor
	 */
	public KrakenAccountService(Exchange exchange) {
		super(exchange);
	}

	@Override
	public AccountInfo getAccountInfo() throws IOException {
		KrakenTradeBalanceInfo krakenTradeBalanceInfo = getKrakenTradeBalance();
		Wallet tradingWallet = KrakenAdapters.adaptWallet(getKrakenBalance());
		Wallet marginWallet =
				Wallet.Builder.from(tradingWallet.getBalances().values())
						.id("margin")
						.features(EnumSet.of(Wallet.WalletFeature.FUNDING, Wallet.WalletFeature.MARGIN_TRADING))
						.maxLeverage(BigDecimal.valueOf(5))
						.currentLeverage(
								(BigDecimal.ZERO.compareTo(krakenTradeBalanceInfo.tradeBalance()) == 0)
										? BigDecimal.ZERO
										: krakenTradeBalanceInfo
										.costBasis()
										.divide(krakenTradeBalanceInfo.tradeBalance(), MathContext.DECIMAL32))
						.build();
		return new AccountInfo(
				exchange.getExchangeSpecification().getUserName(), tradingWallet, marginWallet);
	}

	@Override
	public String withdrawFunds(Currency currency, BigDecimal amount, String address)
			throws IOException {
		return withdraw(null, KrakenUtils.getKrakenCurrencyCode(currency), address, amount).refid();
	}

	@Override
	public String withdrawFunds(WithdrawFundsParams params) throws IOException {
		if (params instanceof DefaultWithdrawFundsParams defaultParams) {
			return withdrawFunds(
					defaultParams.getCurrency(), defaultParams.getAmount(), defaultParams.getAddress());
		}
		throw new IllegalStateException("Don't know how to withdraw: " + params);
	}

	@Override
	public String requestDepositAddress(Currency currency, String... args) throws IOException {
		KrakenDepositAddress[] depositAddresses;
		if (BTC.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Bitcoin", false);
		} else if (LTC.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Litecoin", false);
		} else if (ETH.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Ethereum (ERC20)", false);
		} else if (ZEC.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Zcash (Transparent)", false);
		} else if (ADA.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "ADA", false);
		} else if (XMR.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Monero", false);
		} else if (XRP.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Ripple XRP", false);
		} else if (XLM.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Stellar XLM", false);
		} else if (BCH.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Bitcoin Cash", false);
		} else if (REP.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "REP", false);
		} else if (USD.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "SynapsePay (US Wire)", false);
		} else if (XDG.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Dogecoin", false);
		} else if (MLN.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "MLN", false);
		} else if (GNO.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "GNO", false);
		} else if (QTUM.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "QTUM", false);
		} else if (XTZ.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "XTZ", false);
		} else if (ATOM.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Cosmos", false);
		} else if (EOS.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "EOS", false);
		} else if (DASH.equals(currency)) {
			depositAddresses = getDepositAddresses(currency.toString(), "Dash", false);
		} else {
			throw new RuntimeException("Not implemented yet, Kraken works only for BTC and LTC");
		}
		return KrakenAdapters.adaptKrakenDepositAddress(depositAddresses);
	}

	@Override
	public TradeHistoryParams createFundingHistoryParams() {
		return new KrakenFundingHistoryParams(null, null, null, (Currency[]) null);
	}

	@Override
	public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {
		Date startTime = null;
		Date endTime = null;
		if (params instanceof TradeHistoryParamsTimeSpan timeSpanParam) {
			startTime = timeSpanParam.getStartTime();
			endTime = timeSpanParam.getEndTime();
		}
		Long offset = null;
		if (params instanceof TradeHistoryParamOffset) {
			offset = ((TradeHistoryParamOffset) params).getOffset();
		}
		Currency[] currencies = null;
		if (params instanceof TradeHistoryParamCurrencies currenciesParam) {
			if (currenciesParam.getCurrencies() != null) {
				currencies = currenciesParam.getCurrencies();
			}
		}
		LedgerType ledgerType = null;
		if (params instanceof HistoryParamsFundingType) {
			final FundingRecord.Type type = ((HistoryParamsFundingType) params).getType();
			ledgerType =
					type == FundingRecord.Type.DEPOSIT
							? LedgerType.DEPOSIT
							: type == FundingRecord.Type.WITHDRAWAL ? LedgerType.WITHDRAWAL : null;
		}
		if (ledgerType == null) {
			Map<String, KrakenLedger> ledgerEntries =
					getKrakenLedgerInfo(LedgerType.DEPOSIT, startTime, endTime, offset, currencies);
			ledgerEntries.putAll(
					getKrakenLedgerInfo(LedgerType.WITHDRAWAL, startTime, endTime, offset, currencies));
			return KrakenAdapters.adaptFundingHistory(ledgerEntries);
		} else {
			return KrakenAdapters.adaptFundingHistory(
					getKrakenLedgerInfo(ledgerType, startTime, endTime, offset, currencies));
		}
	}

	@Override
	public Map<Instrument, Fee> getDynamicTradingFeesByInstrument() throws IOException {
		return KrakenAdapters.adaptFees(
				super.getTradeVolume(
						exchange.getExchangeMetaData()
								.getInstruments()
								.keySet()
								.toArray(new CurrencyPair[0])));
	}

	public static class KrakenFundingHistoryParams extends DefaultTradeHistoryParamsTimeSpan
			implements TradeHistoryParamOffset, TradeHistoryParamCurrencies, HistoryParamsFundingType {

		private Long offset;
		private Currency[] currencies;
		private FundingRecord.Type type;

		public KrakenFundingHistoryParams(final Date startTime, final Date endTime, final Long offset, final Currency... currencies) {
			super(startTime, endTime);
			this.offset = offset;
			this.currencies = currencies;
		}

		@Override
		public Long getOffset() {
			return offset;
		}

		@Override
		public void setOffset(final Long offset) {
			this.offset = offset;
		}

		@Override
		public Currency[] getCurrencies() {
			return this.currencies;
		}

		@Override
		public void setCurrencies(Currency[] currencies) {
			this.currencies = currencies;
		}

		@Override
		public FundingRecord.Type getType() {
			return type;
		}

		@Override
		public void setType(FundingRecord.Type type) {
			this.type = type;
		}
	}
}