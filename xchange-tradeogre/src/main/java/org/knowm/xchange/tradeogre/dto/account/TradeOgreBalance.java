package org.knowm.xchange.tradeogre.dto.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeOgreBalance {
	private BigDecimal balance;
	private BigDecimal available;
}