package org.knowm.xchange.bl3p.service.params;

import org.knowm.xchange.bl3p.Bl3pUtils;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

import java.math.BigDecimal;

public abstract class Bl3pWithdrawFundsParams implements WithdrawFundsParams {

	private final String currency;
	private final long amount;

	public Bl3pWithdrawFundsParams(String currency, long amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public long getAmount() {
		return this.amount;
	}

	public static class Euros extends Bl3pWithdrawFundsParams {

		private final String accountId;
		private final String accountName;

		public Euros(String accountId, String accountName, BigDecimal amount) {
			super("EUR", Bl3pUtils.toEuroshi(amount));
			this.accountId = accountId;
			this.accountName = accountName;
		}

		public String getAccountId() {
			return accountId;
		}

		public String getAccountName() {
			return accountName;
		}
	}

	public static class Coins extends Bl3pWithdrawFundsParams {

		private final String address;
		private final boolean extraFee;

		public Coins(String currency, String address, BigDecimal amount) {
			this(currency, address, amount, false);
		}

		public Coins(String currency, String address, BigDecimal amount, boolean extraFee) {
			super(currency, Bl3pUtils.toSatoshi(amount));
			this.address = address;
			this.extraFee = extraFee;
		}

		public String getAddress() {
			return address;
		}

		public boolean isExtraFee() {
			return extraFee;
		}
	}
}