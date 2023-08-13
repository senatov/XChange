package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.knowm.xchange.currency.Currency;

import java.math.BigDecimal;

import static org.knowm.xchange.currency.Currency.getInstance;

public final class BinanceBalance {

	@Getter
	private final Currency currency;
	private final BigDecimal free;
	@Getter
	private final BigDecimal locked;

	public BinanceBalance(
			@JsonProperty("asset") String asset,
			@JsonProperty("free") BigDecimal free,
			@JsonProperty("locked") BigDecimal locked) {
		this.currency = getInstance(asset);
		this.locked = locked;
		this.free = free;
	}

	public BigDecimal getTotal() {
		return free.add(locked);
	}

	public BigDecimal getAvailable() {
		return free;
	}

	public String toString() {
		return "[" + currency + ", free=" + free + ", locked=" + locked + "]";
	}
}