package org.knowm.xchange.bibox.dto.trade;

import org.knowm.xchange.bibox.dto.BiboxCommand;

import java.math.BigDecimal;

/**
 * @author odrotleff
 */
public class BiboxTradeCommand extends BiboxCommand<BiboxTradeCommandBody> {

	public BiboxTradeCommand(
			String pair,
			int accountType,
			int orderType,
			int orderSide,
			boolean payBix,
			BigDecimal price,
			BigDecimal amount,
			BigDecimal money) {
		super(
				"orderpending/trade",
				new BiboxTradeCommandBody(
						pair, accountType, orderType, orderSide, payBix, price, amount, money));
	}
}