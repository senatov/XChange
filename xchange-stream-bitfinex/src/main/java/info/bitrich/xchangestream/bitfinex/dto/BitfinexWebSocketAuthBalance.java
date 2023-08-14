package info.bitrich.xchangestream.bitfinex.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class BitfinexWebSocketAuthBalance {
	private final String walletType;
	private final String currency;
	private final BigDecimal balance;
	private final BigDecimal unsettledInterest;
	private final BigDecimal balanceAvailable;

	public BitfinexWebSocketAuthBalance(
			String walletType,
			String currency,
			BigDecimal balance,
			BigDecimal unsettledInterest,
			BigDecimal balanceAvailable) {
		this.walletType = walletType;
		this.currency = currency;
		this.balance = balance;
		this.unsettledInterest = unsettledInterest;
		this.balanceAvailable = balanceAvailable;
	}

	public String getWalletType() {
		return walletType;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public BigDecimal getUnsettledInterest() {
		return unsettledInterest;
	}

	public BigDecimal getBalanceAvailable() {
		return balanceAvailable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(walletType, currency, balance, unsettledInterest, balanceAvailable);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BitfinexWebSocketAuthBalance that))
			return false;
		return Objects.equals(walletType, that.walletType)
				&& Objects.equals(currency, that.currency)
				&& Objects.equals(balance, that.balance)
				&& Objects.equals(unsettledInterest, that.unsettledInterest)
				&& Objects.equals(balanceAvailable, that.balanceAvailable);
	}

	@Override
	public String toString() {
		return "BitfinexWebSocketAuthBalance{"
				+ "walletType='"
				+ walletType
				+ '\''
				+ ", currency='"
				+ currency
				+ '\''
				+ ", balance="
				+ balance
				+ ", unsettledInterest="
				+ unsettledInterest
				+ ", balanceAvailable="
				+ balanceAvailable
				+ '}';
	}
}