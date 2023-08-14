package info.bitrich.xchangestream.cexio;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;

import java.math.BigDecimal;
import java.util.Date;

public class CexioOrder extends LimitOrder {

	private BigDecimal remainingAmount;

	public CexioOrder(
			CurrencyPair currencyPair, String id, OrderStatus status, BigDecimal remainingAmount) {
		this(null, currencyPair, null, id, null, null, null, status);
		this.remainingAmount = remainingAmount;
	}

	public CexioOrder(
			OrderType type,
			CurrencyPair currencyPair,
			BigDecimal originalAmount,
			String id,
			Date timestamp,
			BigDecimal limitPrice,
			BigDecimal fee,
			OrderStatus status) {
		super(type, originalAmount, currencyPair, id, timestamp, limitPrice, null, null, fee, status);
		this.remainingAmount = null;
	}

	@Override
	public BigDecimal getRemainingAmount() {
		if (remainingAmount != null) {
			return remainingAmount;
		}
		return super.getRemainingAmount();
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (remainingAmount != null ? remainingAmount.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CexioOrder that))
			return false;
		if (!super.equals(o))
			return false;
		return remainingAmount != null
				? remainingAmount.compareTo(that.remainingAmount) == 0
				: that.remainingAmount == null;
	}
}