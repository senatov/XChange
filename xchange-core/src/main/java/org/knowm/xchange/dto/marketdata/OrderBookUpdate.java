package org.knowm.xchange.dto.marketdata;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.instrument.Instrument;

/** Immutable data object representing a Market Depth update. */
public final class OrderBookUpdate implements Serializable {

  @Serial
  private static final long serialVersionUID = -7283757982319511254L;

  private final LimitOrder limitOrder;

  /** this is the total volume at this price in the order book */
  private final BigDecimal totalVolume;

  /**
   * Build an order book update.
   *
   * @param type the order type (BID/ASK)
   * @param volume volume of the limit order in the base currency (i.e. BTC for BTC/USD)
   * @param instrument the instrument traded (e.g. BTC/USD)
   * @param limitPrice the price of this update in counter currency per base currency (i.e. $/BTC in
   *     BTC/USD)
   * @param timestamp the timestamp for the update
   * @param totalVolume the total new volume of open orders for this price in the order book, in the
   *     base currency
   */
  // TODO clarify what should be provided for volume parameter
  public OrderBookUpdate(
      OrderType type,
      BigDecimal volume,
      Instrument instrument,
      BigDecimal limitPrice,
      Date timestamp,
      BigDecimal totalVolume) {

    this.limitOrder = new LimitOrder(type, volume, instrument, "", timestamp, limitPrice);
    this.totalVolume = totalVolume;
  }

  /**
   * Get the limit order.
   *
   * @return the limit order
   */
  public LimitOrder getLimitOrder() {

    return limitOrder;
  }

  /**
   * Get the total volume.
   *
   * @return the total volume
   */
  public BigDecimal getTotalVolume() {

    return totalVolume;
  }

  @Override
  public String toString() {

    return "OrderBookUpdate [limitOrder=" + limitOrder + ", totalVolume=" + totalVolume + "]";
  }
}