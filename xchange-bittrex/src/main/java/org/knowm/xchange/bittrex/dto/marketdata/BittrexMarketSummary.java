package org.knowm.xchange.bittrex.dto.marketdata;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class BittrexMarketSummary {
	private String symbol;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal volume;
	private BigDecimal quoteVolume;
	private BigDecimal percentChange;
	private Date updatedAt;
}