package org.knowm.xchange.bittrex.dto.marketdata;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BittrexTicker {
	private String symbol;
	private BigDecimal lastTradeRate;
	private BigDecimal bidRate;
	private BigDecimal askRate;
}