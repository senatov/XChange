package org.knowm.xchange.kraken;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderStatus;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Fee;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.OpenPosition;
import org.knowm.xchange.dto.account.OpenPositions;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.FeeTier;
import org.knowm.xchange.dto.meta.InstrumentMetaData;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.kraken.dto.account.KrakenDepositAddress;
import org.knowm.xchange.kraken.dto.account.KrakenLedger;
import org.knowm.xchange.kraken.dto.account.KrakenTradeVolume;
import org.knowm.xchange.kraken.dto.account.KrakenVolumeFee;
import org.knowm.xchange.kraken.dto.marketdata.KrakenAsset;
import org.knowm.xchange.kraken.dto.marketdata.KrakenAssetPair;
import org.knowm.xchange.kraken.dto.marketdata.KrakenDepth;
import org.knowm.xchange.kraken.dto.marketdata.KrakenFee;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicOrder;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicTrade;
import org.knowm.xchange.kraken.dto.marketdata.KrakenTicker;
import org.knowm.xchange.kraken.dto.trade.KrakenOpenPosition;
import org.knowm.xchange.kraken.dto.trade.KrakenOrder;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderDescription;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderResponse;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderStatus;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderType;
import org.knowm.xchange.kraken.dto.trade.KrakenTrade;
import org.knowm.xchange.kraken.dto.trade.KrakenType;
import org.knowm.xchange.kraken.dto.trade.KrakenUserTrade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class KrakenAdapters {

	public static OrderBook adaptOrderBook(KrakenDepth krakenDepth, CurrencyPair currencyPair) {
		OrdersContainer asksOrdersContainer =
				adaptOrders(krakenDepth.asks(), currencyPair, OrderType.ASK);
		OrdersContainer bidsOrdersContainer =
				adaptOrders(krakenDepth.bids(), currencyPair, OrderType.BID);
		return new OrderBook(
				new Date(Math.max(asksOrdersContainer.timestamp(), bidsOrdersContainer.timestamp())),
				asksOrdersContainer.limitOrders(),
				bidsOrdersContainer.limitOrders());
	}

	public static OrdersContainer adaptOrders(
			List<KrakenPublicOrder> orders, CurrencyPair currencyPair, OrderType orderType) {
		long maxTimestamp = -1 * Long.MAX_VALUE;
		List<LimitOrder> limitOrders = new ArrayList<>(orders.size());
		for (KrakenPublicOrder order : orders) {
			if (order.timestamp() > maxTimestamp) {
				maxTimestamp = order.timestamp();
			}
			limitOrders.add(adaptOrder(order, orderType, currencyPair));
		}
		return new OrdersContainer(
				(String.valueOf(maxTimestamp).length() >= 13) ? maxTimestamp : maxTimestamp * 1000,
				limitOrders);
	}

	public static LimitOrder adaptOrder(
			KrakenPublicOrder order, OrderType orderType, CurrencyPair currencyPair) {
		Date timeStamp = new Date(order.timestamp() * 1000);
		BigDecimal volume = order.volume();
		return new LimitOrder(orderType, volume, currencyPair, "", timeStamp, order.price());
	}

	public static OpenPositions adaptOpenPositions(
			Map<String, KrakenOpenPosition> krakenOpenPositionMap) {
		List<OpenPosition> openPositionsList = new ArrayList<>();
		krakenOpenPositionMap
				.values()
				.forEach(
						krakenOpenPosition -> openPositionsList.add(
								new OpenPosition.Builder()
										.instrument(new CurrencyPair(krakenOpenPosition.assetPair()))
										.type(
												krakenOpenPosition.type() == KrakenType.BUY
														? OpenPosition.Type.LONG
														: OpenPosition.Type.SHORT)
										.size(krakenOpenPosition.cost())
										.price(
												krakenOpenPosition
														.cost()
														.divide(
																krakenOpenPosition
																		.volume()
																		.subtract(krakenOpenPosition.volumeClosed()),
																RoundingMode.HALF_EVEN))
										.build()));
		return new OpenPositions(openPositionsList);
	}

	public static List<Order> adaptOrders(Map<String, KrakenOrder> krakenOrdersMap) {
		return krakenOrdersMap.entrySet().stream()
				.map(krakenOrderEntry -> adaptOrder(krakenOrderEntry.getKey(), krakenOrderEntry.getValue()))
				.collect(Collectors.toList());
	}

	public static Order adaptOrder(String orderId, KrakenOrder krakenOrder) {
		OrderType orderType = adaptOrderType(krakenOrder.getOrderDescription().type());
		CurrencyPair currencyPair = adaptCurrencyPair(krakenOrder.getOrderDescription().assetPair());
		OrderStatus orderStatus = adaptOrderStatus(krakenOrder.getStatus());
		BigDecimal filledAmount = krakenOrder.getVolumeExecuted();
		BigDecimal originalAmount = krakenOrder.getVolume();
		BigDecimal fee = krakenOrder.getFee();
		if (orderStatus == OrderStatus.NEW
				&& filledAmount.compareTo(BigDecimal.ZERO) > 0
				&& filledAmount.compareTo(originalAmount) < 0) {
			orderStatus = OrderStatus.PARTIALLY_FILLED;
		}
		Double time = krakenOrder.getOpenTimestamp() * 1000; // eg: "opentm":1519731205.9987
		Date timestamp = new Date(time.longValue());
		if (krakenOrder.getOrderDescription().orderType().equals(KrakenOrderType.LIMIT))
			return new LimitOrder(
					orderType,
					krakenOrder.getVolume(),
					currencyPair,
					orderId,
					timestamp,
					krakenOrder.getOrderDescription().price(),
					krakenOrder.getPrice(),
					krakenOrder.getVolumeExecuted(),
					fee,
					orderStatus,
					krakenOrder.getUserRefId());
		if (krakenOrder.getOrderDescription().orderType().equals(KrakenOrderType.MARKET))
			return new MarketOrder(
					orderType,
					krakenOrder.getVolume(),
					currencyPair,
					orderId,
					timestamp,
					krakenOrder.getPrice(),
					krakenOrder.getVolumeExecuted(),
					fee,
					orderStatus,
					krakenOrder.getUserRefId());
		throw new NotYetImplementedForExchangeException();
	}

	public static CurrencyPair adaptCurrencyPair(String krakenCurrencyPair) {
		return KrakenUtils.translateKrakenCurrencyPair(krakenCurrencyPair);
	}

	public static OrderType adaptOrderType(KrakenType krakenType) {
		return krakenType.equals(KrakenType.BUY) ? OrderType.BID : OrderType.ASK;
	}

	public static OrderStatus adaptOrderStatus(KrakenOrderStatus status) {
		return switch (status) {
			case PENDING -> OrderStatus.PENDING_NEW;
			case OPEN -> OrderStatus.NEW;
			case CLOSED -> OrderStatus.FILLED;
			case CANCELED -> OrderStatus.CANCELED;
			case EXPIRED -> OrderStatus.EXPIRED;
		};
	}

	public static List<Ticker> adaptTickers(Map<String, KrakenTicker> krakenTickers) {
		List<Ticker> tickers = new ArrayList<>();
		for (Entry<String, KrakenTicker> ticker : krakenTickers.entrySet()) {
			CurrencyPair pair = KrakenUtils.translateKrakenCurrencyPair(ticker.getKey());
			tickers.add(adaptTicker(ticker.getValue(), pair));
		}
		return tickers;
	}

	public static Ticker adaptTicker(KrakenTicker krakenTicker, CurrencyPair currencyPair) {
		Ticker.Builder builder = new Ticker.Builder();
		builder.open(krakenTicker.getOpen());
		builder.ask(krakenTicker.getAsk().price());
		builder.bid(krakenTicker.getBid().price());
		builder.last(krakenTicker.getClose().price());
		builder.high(krakenTicker.get24HourHigh());
		builder.low(krakenTicker.get24HourLow());
		builder.vwap(krakenTicker.get24HourVolumeAvg());
		builder.volume(krakenTicker.get24HourVolume());
		builder.instrument(currencyPair);
		return builder.build();
	}

	public static Trades adaptTrades(
			List<KrakenPublicTrade> krakenTrades, CurrencyPair currencyPair, long last) {
		List<Trade> trades = new ArrayList<>();
		for (KrakenPublicTrade krakenTrade : krakenTrades) {
			trades.add(adaptTrade(krakenTrade, currencyPair));
		}
		return new Trades(trades, last, TradeSortType.SortByTimestamp);
	}

	public static Trade adaptTrade(KrakenPublicTrade krakenPublicTrade, CurrencyPair currencyPair) {
		OrderType type = adaptOrderType(krakenPublicTrade.type());
		BigDecimal originalAmount = krakenPublicTrade.volume();
		Date timestamp = new Date((long) (krakenPublicTrade.time() * 1000L));
		return new Trade.Builder()
				.type(type)
				.originalAmount(originalAmount)
				.instrument(currencyPair)
				.price(krakenPublicTrade.price())
				.timestamp(timestamp)
				.id(String.valueOf((long) (krakenPublicTrade.time() * 10000L)))
				.build();
	}

	public static Wallet adaptWallet(Map<String, BigDecimal> krakenWallet) {
		List<Balance> balances = new ArrayList<>(krakenWallet.size());
		for (Entry<String, BigDecimal> balancePair : krakenWallet.entrySet()) {
			Currency currency;
			try {
				currency = adaptCurrency(balancePair.getKey());
			} catch (Exception e) {
				currency = Currency.getInstance(balancePair.getKey());
			}
			Balance balance = new Balance(currency, balancePair.getValue());
			balances.add(balance);
		}
		return Wallet.Builder.from(balances).build();
	}

	public static Currency adaptCurrency(String krakenCurrencyCode) {
		return KrakenUtils.translateKrakenCurrencyCode(krakenCurrencyCode);
	}

	public static Set<CurrencyPair> adaptCurrencyPairs(Collection<String> krakenCurrencyPairs) {
		Set<CurrencyPair> currencyPairs = new HashSet<>();
		for (String krakenCurrencyPair : krakenCurrencyPairs) {
			CurrencyPair currencyPair = adaptCurrencyPair(krakenCurrencyPair);
			if (currencyPair != null) {
				currencyPairs.add(currencyPair);
			}
		}
		return currencyPairs;
	}

	public static OpenOrders adaptOpenOrders(Map<String, KrakenOrder> krakenOrders) {
		List<LimitOrder> limitOrders = new ArrayList<>();
		for (Entry<String, KrakenOrder> krakenOrderEntry : krakenOrders.entrySet()) {
			KrakenOrder krakenOrder = krakenOrderEntry.getValue();
			KrakenOrderDescription orderDescription = krakenOrder.getOrderDescription();
			if (!"limit".equals(orderDescription.orderType().toString())) {
				// how to handle stop-loss, take-profit, stop-loss-limit, and so on orders?
				// ignore anything but a plain limit order for now
				continue;
			}
			limitOrders.add((LimitOrder) adaptOrder(krakenOrderEntry.getKey(), krakenOrder));
		}
		return new OpenOrders(limitOrders);
	}

	public static UserTrades adaptTradesHistory(Map<String, KrakenTrade> krakenTrades) {
		List<UserTrade> trades = new ArrayList<>();
		for (Entry<String, KrakenTrade> krakenTradeEntry : krakenTrades.entrySet()) {
			trades.add(adaptTrade(krakenTradeEntry.getValue(), krakenTradeEntry.getKey()));
		}
		return new UserTrades(trades, TradeSortType.SortByTimestamp);
	}

	public static KrakenUserTrade adaptTrade(KrakenTrade krakenTrade, String tradeId) {
		OrderType orderType = adaptOrderType(krakenTrade.type());
		BigDecimal originalAmount = krakenTrade.volume();
		String krakenAssetPair = krakenTrade.assetPair();
		CurrencyPair pair = adaptCurrencyPair(krakenAssetPair);
		Date timestamp = new Date((long) (krakenTrade.unixTimestamp() * 1000L));
		BigDecimal averagePrice = krakenTrade.averageClosePrice();
		BigDecimal price = (averagePrice == null) ? krakenTrade.price() : averagePrice;
		return new KrakenUserTrade(
				orderType,
				originalAmount,
				pair,
				price,
				timestamp,
				tradeId,
				krakenTrade.orderTxId(),
				krakenTrade.fee(),
				pair.counter,
				krakenTrade.cost());
	}

	public static String adaptKrakenDepositAddress(KrakenDepositAddress[] krakenDepositAddress) {
		return krakenDepositAddress[0].address();
	}

	public static String adaptOrderId(KrakenOrderResponse orderResponse) {
		List<String> orderIds = orderResponse.getTransactionIds();
		return (orderIds == null || orderIds.isEmpty()) ? "" : orderIds.get(0);
	}

	public static ExchangeMetaData adaptToExchangeMetaData(
			ExchangeMetaData originalMetaData,
			Map<String, KrakenAssetPair> krakenPairs,
			Map<String, KrakenAsset> krakenAssets) {
		Map<Instrument, InstrumentMetaData> pairs = new HashMap<>();
		// add assets before pairs to Utils!
		KrakenUtils.setKrakenAssets(krakenAssets);
		KrakenUtils.setKrakenAssetPairs(krakenPairs);
		for (String krakenPairCode : krakenPairs.keySet()) {
			//  skip dark markets!
			if (!krakenPairCode.endsWith(".d")) {
				KrakenAssetPair krakenPair = krakenPairs.get(krakenPairCode);
				pairs.put(
						adaptCurrencyPair(krakenPairCode),
						adaptPair(krakenPair, pairs.get(adaptCurrencyPair(krakenPairCode))));
			}
		}
		Map<Currency, CurrencyMetaData> currencies = new HashMap<>();
		for (String krakenAssetCode : krakenAssets.keySet()) {
			KrakenAsset krakenAsset = krakenAssets.get(krakenAssetCode);
			Currency currencyCode = KrakenAdapters.adaptCurrency(krakenAssetCode);
			currencies.put(currencyCode, new CurrencyMetaData(krakenAsset.scale(), null));
		}
		return new ExchangeMetaData(
				pairs,
				currencies,
				originalMetaData == null ? null : originalMetaData.getPublicRateLimits(),
				originalMetaData == null ? null : originalMetaData.getPrivateRateLimits(),
				originalMetaData == null ? null : originalMetaData.isShareRateLimits());
	}

	private static InstrumentMetaData adaptPair(
			KrakenAssetPair krakenPair, InstrumentMetaData OriginalMeta) {
		return new InstrumentMetaData.Builder()
				.tradingFee(krakenPair.getFees().get(0).percentFee().divide(new BigDecimal(100)))
				.minimumAmount(krakenPair.getOrderMin())
				.priceScale(krakenPair.getPairScale())
				.volumeScale(krakenPair.getVolumeLotScale())
				.feeTiers(adaptFeeTiers(krakenPair.getFees_maker(), krakenPair.getFees()))
				.tradingFeeCurrency(KrakenUtils.translateKrakenCurrencyCode(krakenPair.getFeeVolumeCurrency()))
				.marketOrderEnabled(true)
				.build();
	}

	protected static FeeTier[] adaptFeeTiers(List<KrakenFee> makerFees, List<KrakenFee> takerFees) {
		Collections.sort(makerFees);
		Collections.sort(takerFees);
		List<FeeTier> resultFeeTiers = new ArrayList<>();
		int makerFeeIdx = 0;
		int takerFeeIdx = 0;
		while (makerFeeIdx < makerFees.size() || takerFeeIdx < takerFees.size()) {
			int curMakerIdx = Math.min(makerFeeIdx, makerFees.size() - 1);
			int curTakerIdx = Math.min(takerFeeIdx, takerFees.size() - 1);
			BigDecimal quantityMaker = makerFees.get(curMakerIdx).volume();
			BigDecimal quantityTaker = takerFees.get(curTakerIdx).volume();
			BigDecimal resultQuantity = null;
			BigDecimal resultMakerFee = null;
			BigDecimal resultTakerFee = null;
			int makerVolCompTakerVol = quantityMaker.compareTo(quantityTaker);
			if ((makerVolCompTakerVol > 0 || makerFeeIdx >= makerFees.size())
					&& takerFeeIdx < takerFees.size()) {
				if (makerFeeIdx < 1) {
					throw new IllegalStateException(
							"Kraken exchange specified fee tiers such that the maker fee was unspecified before a nonzero quantity was traded.");
				}
				KrakenFee takerFeeData = takerFees.get(curTakerIdx);
				resultTakerFee = takerFeeData.percentFee();
				resultMakerFee = makerFees.get(makerFeeIdx - 1).percentFee();
				resultQuantity = takerFeeData.volume();
				takerFeeIdx++;
			} else if (makerVolCompTakerVol < 0 || takerFeeIdx >= takerFees.size()) {
				if (takerFeeIdx < 1) {
					throw new IllegalStateException(
							"Kraken exchange specified fee tiers such that the taker fee was unspecified before a nonzero quantity was traded.");
				}
				KrakenFee makerFeeData = makerFees.get(curMakerIdx);
				resultMakerFee = makerFeeData.percentFee();
				resultTakerFee = takerFees.get(takerFeeIdx - 1).percentFee();
				resultQuantity = makerFeeData.volume();
				makerFeeIdx++;
			} else { // makerVolCompTakerVol == 0 && makerFeeIdx < makerFees.size() && takerFeeIdx <
				// takerFees.size()
				KrakenFee makerFeeData = makerFees.get(curMakerIdx);
				resultMakerFee = makerFeeData.percentFee();
				resultTakerFee = takerFees.get(curTakerIdx).percentFee();
				resultQuantity = makerFeeData.volume();
				takerFeeIdx++;
				makerFeeIdx++;
			}
			resultFeeTiers.add(new FeeTier(resultQuantity, new Fee(resultMakerFee.movePointLeft(2), resultTakerFee.movePointLeft(2))));
		}
		return resultFeeTiers.toArray(new FeeTier[0]);
	}

	public static Map<Instrument, Fee> adaptFees(KrakenTradeVolume krakenTradeVolume) {
		Map<Instrument, Fee> feeMap = new HashMap<>();
		// Compute Taker Fees
		for (Entry<String, KrakenVolumeFee> entry : krakenTradeVolume.getFees().entrySet()) {
			feeMap.computeIfAbsent(
					KrakenUtils.translateKrakenCurrencyPair(entry.getKey()),
					currencyPair -> new Fee(null, entry.getValue().fee().divide(new BigDecimal(100))));
		}
		// Compute Maker Fees
		for (Entry<String, KrakenVolumeFee> entry : krakenTradeVolume.getFeesMaker().entrySet()) {
			feeMap.computeIfPresent(
					KrakenUtils.translateKrakenCurrencyPair(entry.getKey()),
					(currencyPair, fee) ->
							fee =
									new Fee(
											entry.getValue().fee().divide(new BigDecimal(100)), fee.getTakerFee()));
			feeMap.computeIfAbsent(
					KrakenUtils.translateKrakenCurrencyPair(entry.getKey()),
					currencyPair -> new Fee(entry.getValue().fee().divide(new BigDecimal(100)), null));
		}
		return feeMap;
	}

	public static List<FundingRecord> adaptFundingHistory(
			Map<String, KrakenLedger> krakenLedgerInfo) {
		final List<FundingRecord> fundingRecords = new ArrayList<>();
		for (Entry<String, KrakenLedger> ledgerEntry : krakenLedgerInfo.entrySet()) {
			final KrakenLedger krakenLedger = ledgerEntry.getValue();
			if (krakenLedger.ledgerType() != null) {
				final Currency currency = adaptCurrency(krakenLedger.asset());
				if (currency != null) {
					final Date timestamp = new Date((long) (krakenLedger.unixTime() * 1000L));
					final FundingRecord.Type type =
							FundingRecord.Type.fromString(krakenLedger.ledgerType().name());
					if (type != null) {
						final String internalId = krakenLedger.refId(); // or ledgerEntry.getKey()?
						FundingRecord fundingRecordEntry =
								new FundingRecord(
										null,
										timestamp,
										currency,
										krakenLedger.transactionAmount(),
										internalId,
										null,
										FundingRecord.Type.fromString(krakenLedger.ledgerType().name()),
										FundingRecord.Status.COMPLETE,
										krakenLedger.balance(),
										krakenLedger.fee(),
										null);
						fundingRecords.add(fundingRecordEntry);
					}
				}
			}
		}
		return fundingRecords;
	}

	public record OrdersContainer(long timestamp, List<LimitOrder> limitOrders) {

		/**
		 * Constructor
		 */
		public OrdersContainer {
		}


	}
}