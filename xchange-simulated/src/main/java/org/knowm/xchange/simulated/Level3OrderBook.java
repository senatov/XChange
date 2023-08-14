package org.knowm.xchange.simulated;

import lombok.Data;
import org.knowm.xchange.dto.trade.LimitOrder;

import java.util.List;

/**
 * A full order book, consisting of every single limit order on the book on both the ask and bid
 * sides.
 *
 * @author Graham Crockford
 */
@Data
public class Level3OrderBook {
	private final List<LimitOrder> asks;
	private final List<LimitOrder> bids;
}