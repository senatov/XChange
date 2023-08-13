package org.knowm.xchange.kraken.dto.trade;

import lombok.Getter;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.IOrderFlags;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KrakenStandardOrder {

	private final CurrencyPair currencyPair;
	@Getter
	private final KrakenType type;
	@Getter
	private final KrakenOrderType orderType;
	@Getter
	private final String price;
	@Getter
	private final String secondaryPrice;
	@Getter
	private final BigDecimal volume;
	@Getter
	private final String leverage;
	@Getter
	private final String positionTxId;
	@Getter
	private final Set<IOrderFlags> orderFlags;
	@Getter
	private final String startTime;
	@Getter
	private final String expireTime;
	@Getter
	private final String userRefId;
	@Getter
	private final boolean validateOnly;
	@Getter
	private final Map<String, String> closeOrder;
	@Getter
	private final TimeInForce timeInForce;

	private KrakenStandardOrder(
			CurrencyPair currencyPair,
			KrakenType type,
			KrakenOrderType orderType,
			String price,
			String secondaryPrice,
			BigDecimal volume,
			String leverage,
			String positionTxId,
			Set<IOrderFlags> orderFlags,
			String startTime,
			String expireTime,
			String userRefId,
			boolean validateOnly,
			Map<String, String> closeOrder,
			TimeInForce timeInForce) {
		this.currencyPair = currencyPair;
		this.type = type;
		this.orderType = orderType;
		this.price = price;
		this.secondaryPrice = secondaryPrice;
		this.volume = volume;
		this.leverage = leverage;
		this.positionTxId = positionTxId;
		this.orderFlags = orderFlags;
		this.startTime = startTime;
		this.expireTime = expireTime;
		this.userRefId = userRefId;
		this.validateOnly = validateOnly;
		this.closeOrder = closeOrder;
		this.timeInForce = timeInForce;
	}

	public static KrakenOrderBuilder getMarketOrderBuilder(
			CurrencyPair currencyPair, KrakenType type, BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.MARKET, volume);
	}

	public static KrakenOrderBuilder getLimitOrderBuilder(
			CurrencyPair currencyPair, KrakenType type, String limitPrice, BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.LIMIT, volume)
				.withPrice(limitPrice);
	}

	public static KrakenOrderBuilder getStopLossOrderBuilder(
			CurrencyPair currencyPair, KrakenType type, String stopLossPrice, BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.STOP_LOSS, volume)
				.withPrice(stopLossPrice);
	}

	public static KrakenOrderBuilder getTakeProfitOrderBuilder(
			CurrencyPair currencyPair, KrakenType type, String takeProfitPrice, BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.TAKE_PROFIT, volume)
				.withPrice(takeProfitPrice);
	}

	public static KrakenOrderBuilder getStopLossProfitOrderBuilder(
			CurrencyPair currencyPair,
			KrakenType type,
			String stopLossPrice,
			String takeProfitPrice,
			BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.STOP_LOSS_PROFIT, volume)
				.withPrice(stopLossPrice)
				.withSecondaryPrice(takeProfitPrice);
	}

	public static KrakenOrderBuilder getStopLossProfitLimitOrderBuilder(
			CurrencyPair currencyPair,
			KrakenType type,
			String stopLossPrice,
			String takeProfitPrice,
			BigDecimal volume) {
		return new KrakenOrderBuilder(
				currencyPair, type, KrakenOrderType.STOP_LOSS_PROFIT_LIMIT, volume)
				.withPrice(stopLossPrice)
				.withSecondaryPrice(takeProfitPrice);
	}

	public static KrakenOrderBuilder getStopLossLimitOrderBuilder(
			CurrencyPair currencyPair,
			KrakenType type,
			String stopLossTriggerPrice,
			String triggeredLimitPrice,
			BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.STOP_LOSS_LIMIT, volume)
				.withPrice(stopLossTriggerPrice)
				.withSecondaryPrice(triggeredLimitPrice);
	}

	public static KrakenOrderBuilder getTakeProfitLimitOrderBuilder(
			CurrencyPair currencyPair,
			KrakenType type,
			String takeProfitTriggerPrice,
			String triggeredLimitPrice,
			BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.TAKE_PROFIT_LIMIT, volume)
				.withPrice(takeProfitTriggerPrice)
				.withSecondaryPrice(triggeredLimitPrice);
	}

	public static KrakenOrderBuilder getTrailingStopOrderBuilder(
			CurrencyPair currencyPair, KrakenType type, String trailingStopOffset, BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.TRAILING_STOP, volume)
				.withPrice(trailingStopOffset);
	}

	public static KrakenOrderBuilder getTrailingStopLimitOrderBuilder(
			CurrencyPair currencyPair,
			KrakenType type,
			String trailingStopOffset,
			String triggeredLimitOffset,
			BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.TRAILING_STOP_LIMIT, volume)
				.withPrice(trailingStopOffset)
				.withSecondaryPrice(triggeredLimitOffset);
	}

	public static KrakenOrderBuilder getStopLossAndLimitOrderBuilder(
			CurrencyPair currencyPair,
			KrakenType type,
			String stopLossPrice,
			String limitPrice,
			BigDecimal volume) {
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.STOP_LOSS_AND_LIMIT, volume)
				.withPrice(stopLossPrice)
				.withSecondaryPrice(limitPrice);
	}

	public static KrakenOrderBuilder getSettlePositionOrderBuilder(
			CurrencyPair currencyPair, KrakenType type, BigDecimal volume) {
		// Leverage parameter is required but its value is irrelevant for settling position
		return new KrakenOrderBuilder(currencyPair, type, KrakenOrderType.SETTLE_POSITION, volume)
				.withLeverage("2");
	}

	public CurrencyPair getAssetPair() {
		return currencyPair;
	}

	@Override
	public String toString() {
		return "KrakenStandardOrder [currencyPair="
				+ currencyPair
				+ ", type="
				+ type
				+ ", orderType="
				+ orderType
				+ ", price="
				+ price
				+ ", secondaryPrice="
				+ secondaryPrice
				+ ", volume="
				+ volume
				+ ", leverage="
				+ leverage
				+ ", positionTxId="
				+ positionTxId
				+ ", orderFlags="
				+ orderFlags
				+ ", startTime="
				+ startTime
				+ ", expireTime="
				+ expireTime
				+ ", userRefId="
				+ userRefId
				+ ", validateOnly="
				+ validateOnly
				+ ", closeOrder="
				+ closeOrder
				+ ", timeInForce="
				+ timeInForce
				+ "]";
	}

	public static class KrakenOrderBuilder {

		private final CurrencyPair currencyPair;
		@Getter
		private final KrakenType type;
		@Getter
		private final KrakenOrderType orderType;
		@Getter
		private final BigDecimal volume;
		@Getter
		private final Set<IOrderFlags> orderFlags;
		@Getter
		private String price;
		@Getter
		private String secondaryPrice;
		@Getter
		private String leverage;
		@Getter
		private String positionTxId;
		@Getter
		private String startTime;
		@Getter
		private String expireTime;
		@Getter
		private String userRefId;
		@Getter
		private boolean validateOnly;
		@Getter
		private Map<String, String> closeOrder;
		@Getter
		private TimeInForce timeInForce;

		private KrakenOrderBuilder(
				CurrencyPair currencyPair, KrakenType type, KrakenOrderType orderType, BigDecimal volume) {
			this.currencyPair = currencyPair;
			this.type = type;
			this.orderType = orderType;
			this.volume = volume;
			this.orderFlags = new HashSet<>();
			this.startTime = null;
			this.positionTxId = "0";
			this.validateOnly = false;
		}

		public KrakenOrderBuilder withPrice(String price) {
			this.price = price;
			return this;
		}

		public KrakenOrderBuilder withSecondaryPrice(String secondaryPrice) {
			this.secondaryPrice = secondaryPrice;
			return this;
		}

		public KrakenOrderBuilder withLeverage(String leverage) {
			this.leverage = leverage;
			return this;
		}

		public KrakenOrderBuilder withPositionTxId(String positionTxId) {
			this.positionTxId = positionTxId;
			return this;
		}

		public KrakenOrderBuilder withOrderFlags(Set<IOrderFlags> flags) {
			if (flags == null) {
				orderFlags.clear();
			} else {
				orderFlags.addAll(flags);
			}
			return this;
		}

		public KrakenOrderBuilder withStartTime(String startTime) {
			this.startTime = startTime;
			return this;
		}

		public KrakenOrderBuilder withExpireTime(String expireTime) {
			this.expireTime = expireTime;
			return this;
		}

		public KrakenOrderBuilder withUserRefId(String userRefId) {
			this.userRefId = userRefId;
			return this;
		}

		public KrakenOrderBuilder withValidateOnly(boolean validateOnly) {
			this.validateOnly = validateOnly;
			return this;
		}

		public KrakenOrderBuilder withCloseOrder(
				KrakenOrderType orderType, String price, String secondaryPrice) {
			closeOrder = new HashMap<>();
			closeOrder.put("ordertype", orderType.toString());
			closeOrder.put("price", price);
			closeOrder.put("price2", secondaryPrice);
			return this;
		}

		public KrakenOrderBuilder withTimeInForce(TimeInForce timeInForce) {
			this.timeInForce = timeInForce;
			return this;
		}

		public KrakenStandardOrder buildOrder() {
			return new KrakenStandardOrder(
					currencyPair,
					type,
					orderType,
					price,
					secondaryPrice,
					volume,
					leverage,
					positionTxId,
					orderFlags,
					startTime,
					expireTime,
					userRefId,
					validateOnly,
					closeOrder == null ? new HashMap<>() : closeOrder,
					timeInForce);
		}

		@Override
		public String toString() {
			return "KrakenOrderBuilder [currencyPair="
					+ currencyPair
					+ ", type="
					+ type
					+ ", orderType="
					+ orderType
					+ ", price="
					+ price
					+ ", secondaryPrice="
					+ secondaryPrice
					+ ", volume="
					+ volume
					+ ", leverage="
					+ leverage
					+ ", positionTxId="
					+ positionTxId
					+ ", orderFlags="
					+ orderFlags
					+ ", startTime="
					+ startTime
					+ ", expireTime="
					+ expireTime
					+ ", userRefId="
					+ userRefId
					+ ", validateOnly="
					+ validateOnly
					+ ", closeOrder="
					+ closeOrder
					+ ", timeInForce="
					+ timeInForce
					+ "]";
		}

		public CurrencyPair getAssetPair() {
			return currencyPair;
		}

	}
}