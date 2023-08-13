package org.knowm.xchange.kraken.dto.trade;

import org.knowm.xchange.dto.Order;

public enum TimeInForce implements Order.IOrderFlags {
	GTC,
	IOC,
	GTD
}