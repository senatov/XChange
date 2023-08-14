package org.knowm.xchange.tradeogre.dto.trade;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeOgreOrder {
	public String uuid;
	public long date;
	public String type;
	public BigDecimal price;
	public BigDecimal quantity;
	public String market;
}