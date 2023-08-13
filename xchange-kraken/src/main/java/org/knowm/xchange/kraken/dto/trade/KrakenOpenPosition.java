package org.knowm.xchange.kraken.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderFlags.KrakenOrderFlagsDeserializer;

import java.math.BigDecimal;
import java.util.Set;

public record KrakenOpenPosition(String orderTxId, String assetPair, long tradeUnixTimestamp, KrakenType type,
                                 KrakenOrderType orderType, BigDecimal cost, BigDecimal fee, BigDecimal volume,
                                 BigDecimal volumeClosed, BigDecimal margin, BigDecimal value, BigDecimal netDifference,
                                 String miscellaneous, Set<KrakenOrderFlags> orderFlags,
                                 BigDecimal volumeInQuoteCurrency) {

	/**
	 * Constructor
	 */
	public KrakenOpenPosition(
			@JsonProperty("ordertxid") String orderTxId,
			@JsonProperty("pair") String assetPair,
			@JsonProperty("time") long tradeUnixTimestamp,
			@JsonProperty("type") KrakenType type,
			@JsonProperty("ordertype") KrakenOrderType orderType,
			@JsonProperty("cost") BigDecimal cost,
			@JsonProperty("fee") BigDecimal fee,
			@JsonProperty("vol") BigDecimal volume,
			@JsonProperty("vol_closed") BigDecimal volumeClosed,
			@JsonProperty("margin") BigDecimal margin,
			@JsonProperty("volue") BigDecimal value,
			@JsonProperty("net") BigDecimal netDifference,
			@JsonProperty("misc") String miscellaneous,
			@JsonProperty("oflags") @JsonDeserialize(using = KrakenOrderFlagsDeserializer.class)
			Set<KrakenOrderFlags> orderFlags,
			@JsonProperty("viqc") BigDecimal volumeInQuoteCurrency) {
		this.orderTxId = orderTxId;
		this.assetPair = assetPair;
		this.tradeUnixTimestamp = tradeUnixTimestamp;
		this.type = type;
		this.orderType = orderType;
		this.cost = cost;
		this.fee = fee;
		this.volume = volume;
		this.volumeClosed = volumeClosed;
		this.margin = margin;
		this.value = value;
		this.netDifference = netDifference;
		this.miscellaneous = miscellaneous;
		this.orderFlags = orderFlags;
		this.volumeInQuoteCurrency = volumeInQuoteCurrency;
	}


	@Override
	public String toString() {
		return "KrakenOpenPosition [orderTxId="
				+ orderTxId
				+ ", assetPair="
				+ assetPair
				+ ", tradeUnixTimestamp="
				+ tradeUnixTimestamp
				+ ", type="
				+ type
				+ ", orderType="
				+ orderType
				+ ", cost="
				+ cost
				+ ", fee="
				+ fee
				+ ", volume="
				+ volume
				+ ", volumeClosed="
				+ volumeClosed
				+ ", margin="
				+ margin
				+ ", value="
				+ value
				+ ", netDifference="
				+ netDifference
				+ ", miscellaneous="
				+ miscellaneous
				+ ", orderFlags="
				+ orderFlags
				+ ", volumeInQuoteCurrency="
				+ volumeInQuoteCurrency
				+ "]";
	}
}