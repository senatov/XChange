package org.knowm.xchange.latoken.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response schema:
 * <pre>
 * {
 * 	"orderId": "1555492358.126073.126767@0502:2",
 * 	"cliOrdId": "myNewOrder",
 * 	"pairId": 502,
 * 	"symbol": "LAETH",
 * 	"side": "buy",
 * 	"orderType": "limit",
 * 	"price": 136.2,
 * 	"amount": 0.57,
 * }
 * </pre>
 *
 * @author Ezer
 */
public class LatokenNewOrder {

	private final String orderId;
	private final String clientOrderId;
	private final long pairId;
	private final String symbol;
	private final LatokenOrderSide side;
	private final OrderSubclass type;
	private final BigDecimal price;
	private final BigDecimal amount;

	/**
	 * C'tor
	 */
	public LatokenNewOrder(
			@JsonProperty("orderId") String orderId,
			@JsonProperty("clientOrderId") String clientOrderId,
			@JsonProperty("pairId") long pairId,
			@JsonProperty("symbol") String symbol,
			@JsonProperty("side") String side,
			@JsonProperty("orderType") String type,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("amount") BigDecimal amount) {
		this.orderId = orderId;
		this.clientOrderId = clientOrderId;
		this.pairId = pairId;
		this.symbol = symbol;
		this.side = LatokenOrderSide.parse(side);
		this.type = OrderSubclass.parse(type);
		this.price = price;
		this.amount = amount;
	}

	/**
	 * Id of order
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Id of order provided by client (optional)
	 */
	public String getClientOrderId() {
		return clientOrderId;
	}

	/**
	 * ID of trading pair
	 */
	public long getPairId() {
		return pairId;
	}

	/**
	 * Trading pair symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Order side
	 */
	public LatokenOrderSide getSide() {
		return side;
	}

	/**
	 * Order type
	 */
	public OrderSubclass getType() {
		return type;
	}

	/**
	 * Order price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Order amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "LatokenNewOrder [orderId = "
				+ orderId
				+ ", clientOrderId = "
				+ clientOrderId
				+ ", symbol = "
				+ symbol
				+ ", side = "
				+ side
				+ ", type = "
				+ type
				+ ", price = "
				+ price
				+ ", amount = "
				+ amount
				+ "]";
	}
}