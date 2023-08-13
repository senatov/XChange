package org.knowm.xchange.kraken.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderFlags.KrakenOrderFlagsDeserializer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
public class KrakenOrder {

	private final String refId;
	private final String userRefId;
	private final KrakenOrderStatus status;
	private final double openTimestamp;
	private final double startTimestamp;
	private final double expireTimestamp;
	private final KrakenOrderDescription orderDescription;
	private final BigDecimal volume;
	private final BigDecimal volumeExecuted;
	private final BigDecimal cost;
	private final BigDecimal fee;
	private final BigDecimal price;
	private final BigDecimal stopPrice;
	private final BigDecimal limitPrice;
	private final String miscellaneous;
	private final Set<KrakenOrderFlags> orderFlags;
	private final List<String> tradeIds;
	private final double closeTimestamp;
	private final String closeReason;

	/**
	 * Constructor
	 */
	public KrakenOrder(
			@JsonProperty("refid") String refId,
			@JsonProperty("userref") String userRefId,
			@JsonProperty("status") KrakenOrderStatus status,
			@JsonProperty("opentm") double openTimestamp,
			@JsonProperty("starttm") double startTimestamp,
			@JsonProperty("expiretm") double expireTimestamp,
			@JsonProperty("descr") KrakenOrderDescription orderDescription,
			@JsonProperty("vol") BigDecimal volume,
			@JsonProperty("vol_exec") BigDecimal volumeExecuted,
			@JsonProperty("cost") BigDecimal cost,
			@JsonProperty("fee") BigDecimal fee,
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("stopprice") BigDecimal stopPrice,
			@JsonProperty("limitprice") BigDecimal limitPrice,
			@JsonProperty("misc") String misc,
			@JsonProperty("oflags") @JsonDeserialize(using = KrakenOrderFlagsDeserializer.class)
			Set<KrakenOrderFlags> orderFLags,
			@JsonProperty("trades") List<String> tradeIds,
			@JsonProperty("closetm") double closeTimestamp,
			@JsonProperty("reason") String closeReason) {
		this.refId = refId;
		this.userRefId = userRefId;
		this.status = status;
		this.openTimestamp = openTimestamp;
		this.startTimestamp = startTimestamp;
		this.expireTimestamp = expireTimestamp;
		this.orderDescription = orderDescription;
		this.volume = volume;
		this.volumeExecuted = volumeExecuted;
		this.cost = cost;
		this.fee = fee;
		this.price = price;
		this.stopPrice = stopPrice;
		this.limitPrice = limitPrice;
		this.miscellaneous = misc;
		this.orderFlags = orderFLags;
		this.tradeIds = tradeIds;
		this.closeTimestamp = closeTimestamp;
		this.closeReason = closeReason;
	}

	@Override
	public String toString() {
		return "KrakenOrder [refId="
				+ refId
				+ ", userRefId="
				+ userRefId
				+ ", status="
				+ status
				+ ", openTimestamp="
				+ openTimestamp
				+ ", startTimestamp="
				+ startTimestamp
				+ ", expireTimestamp="
				+ expireTimestamp
				+ ", orderDescription="
				+ orderDescription
				+ ", volume="
				+ volume
				+ ", volumeExecuted="
				+ volumeExecuted
				+ ", cost="
				+ cost
				+ ", fee="
				+ fee
				+ ", price="
				+ price
				+ ", stopPrice="
				+ stopPrice
				+ ", limitPrice="
				+ limitPrice
				+ ", miscellaneous="
				+ miscellaneous
				+ ", orderFlags="
				+ orderFlags
				+ ", tradeIds="
				+ tradeIds
				+ ", closeTimestamp="
				+ closeTimestamp
				+ ", closeReason="
				+ closeReason
				+ "]";
	}
}