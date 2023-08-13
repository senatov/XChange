package org.knowm.xchange.kraken.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record KrakenTrade(String orderTxId, String assetPair, double unixTimestamp, KrakenType type,
                          KrakenOrderType orderType, BigDecimal price, BigDecimal cost, BigDecimal fee,
                          BigDecimal volume, BigDecimal margin, String miscellaneous, String closing,
                          String positionStatus, BigDecimal averageClosePrice, BigDecimal closeCost,
                          BigDecimal closeFee, BigDecimal closeVolume, BigDecimal closeMargin, BigDecimal netDifference,
                          List<String> tradeIds) {

	/**
	 * Constructor
	 */
	public KrakenTrade(
			@JsonProperty("ordertxid") String orderTxId,
			@JsonProperty("pair") String assetPair,
			@JsonProperty("time") double unixTimestamp,
			@JsonProperty("type") KrakenType type,
			@JsonProperty("ordertype") KrakenOrderType orderType,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("cost") BigDecimal cost,
			@JsonProperty("fee") BigDecimal fee,
			@JsonProperty("vol") BigDecimal volume,
			@JsonProperty("margin") BigDecimal margin,
			@JsonProperty("misc") String miscellaneous,
			@JsonProperty("closing") String closing,
			@JsonProperty("posstatus") String positionStatus,
			@JsonProperty("cprice") BigDecimal averageClosePrice,
			@JsonProperty("ccost") BigDecimal closeCost,
			@JsonProperty("cfee") BigDecimal closeFee,
			@JsonProperty("cvol") BigDecimal closeVolume,
			@JsonProperty("cmargin") BigDecimal closeMargin,
			@JsonProperty("net") BigDecimal netDifference,
			@JsonProperty("trades") List<String> tradeIds) {
		this.orderTxId = orderTxId;
		this.assetPair = assetPair;
		this.unixTimestamp = unixTimestamp;
		this.type = type;
		this.orderType = orderType;
		this.price = price;
		this.cost = cost;
		this.fee = fee;
		this.volume = volume;
		this.margin = margin;
		this.miscellaneous = miscellaneous;
		this.closing = closing;
		this.positionStatus = positionStatus;
		this.averageClosePrice = averageClosePrice;
		this.closeCost = closeCost;
		this.closeFee = closeFee;
		this.closeVolume = closeVolume;
		this.closeMargin = closeMargin;
		this.netDifference = netDifference;
		this.tradeIds = tradeIds;
	}


	@Override
	public String toString() {
		return "KrakenTrade [orderTxId="
				+ orderTxId
				+ ", assetPair="
				+ assetPair
				+ ", unixTimestamp="
				+ unixTimestamp
				+ ", type="
				+ type
				+ ", orderType="
				+ orderType
				+ ", price="
				+ price
				+ ", cost="
				+ cost
				+ ", fee="
				+ fee
				+ ", volume="
				+ volume
				+ ", margin="
				+ margin
				+ ", miscellaneous="
				+ miscellaneous
				+ ", closing="
				+ closing
				+ ", positionStatus="
				+ positionStatus
				+ ", averageClosePrice="
				+ averageClosePrice
				+ ", closeCost="
				+ closeCost
				+ ", closeFee="
				+ closeFee
				+ ", closeVolume="
				+ closeVolume
				+ ", closeMargin="
				+ closeMargin
				+ ", netDifference="
				+ netDifference
				+ ", tradeIds="
				+ tradeIds
				+ "]";
	}
}