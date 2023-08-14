package org.knowm.xchange.cexio.dto;

import org.knowm.xchange.cexio.dto.trade.CexIOOrder;

import java.math.BigDecimal;

public class CexioPlaceOrderRequest extends CexIORequest {
	public final CexIOOrder.Type type;
	public final BigDecimal price;
	public final BigDecimal amount;
	public final String order_type;

	public CexioPlaceOrderRequest(
			CexIOOrder.Type type, BigDecimal price, BigDecimal amount, String orderType) {
		this.type = type;
		this.price = price;
		this.amount = amount;
		this.order_type = orderType;
	}
}