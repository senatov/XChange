package org.knowm.xchange.tradeogre.dto.account;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class TradeOgreBalances {
	private Map<String, BigDecimal> balances;
}