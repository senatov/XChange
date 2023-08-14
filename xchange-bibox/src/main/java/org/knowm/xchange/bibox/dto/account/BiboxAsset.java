package org.knowm.xchange.bibox.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.knowm.xchange.currency.Currency;

import java.math.BigDecimal;

public class BiboxAsset {

	@JsonProperty("coin_symbol")
	@Getter
	private Currency coin_symbol;

	@JsonProperty("BTCValue")
	@Getter
	private BigDecimal BTCValue;

	@JsonProperty("CNYValue")
	@Getter
	private BigDecimal CNYValue;

	@JsonProperty("USDValue")
	@Getter
	private BigDecimal USDValue;

	@JsonProperty("balance")
	@Getter
	private BigDecimal balance;

	@JsonProperty("freeze")
	@Getter
	private BigDecimal freeze;
}