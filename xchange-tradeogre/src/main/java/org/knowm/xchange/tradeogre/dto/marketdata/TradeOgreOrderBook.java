package org.knowm.xchange.tradeogre.dto.marketdata;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class TradeOgreOrderBook {
	public boolean success;
	public Map<BigDecimal, BigDecimal> buy;
	public Map<BigDecimal, BigDecimal> sell;
}