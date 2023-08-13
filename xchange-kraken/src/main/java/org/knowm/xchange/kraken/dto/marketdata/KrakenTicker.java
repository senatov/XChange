package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Data object representing depth from Kraken
 */
public class KrakenTicker {

	@Getter
	private final KrakenPublicOrder ask; // ask array(<price>, <lot volume>),
	@Getter
	private final KrakenPublicOrder bid; // bid array(<price>, <lot volume>),
	@Getter
	private final KrakenPublicOrder close; // last trade closed array(<price>, <lot volume>),
	private final BigDecimal[] volume; // volume array(<today>, <last 24 hours>),
	private final BigDecimal[]
			volumeAvg; // volume weighted average price array(<today>, <last 24 hours>),
	private final BigDecimal[] trades; // number of trades array(<today>, <last 24 hours>),
	private final BigDecimal[] low; // low array(<today>, <last 24 hours>),
	private final BigDecimal[] high; // high array(<today>, <last 24 hours>),
	@Getter
	private final BigDecimal open; // today's opening price

	/**
	 * Constructor
	 */
	public KrakenTicker(
			@JsonProperty("a") KrakenPublicOrder ask,
			@JsonProperty("b") KrakenPublicOrder bid,
			@JsonProperty("c") KrakenPublicOrder close,
			@JsonProperty("v") BigDecimal[] volume,
			@JsonProperty("p") BigDecimal[] volumeAvg,
			@JsonProperty("t") BigDecimal[] trades,
			@JsonProperty("l") BigDecimal[] low,
			@JsonProperty("h") BigDecimal[] high,
			@JsonProperty("o") BigDecimal open) {
		this.ask = ask;
		this.bid = bid;
		this.close = close;
		this.volume = volume;
		this.volumeAvg = volumeAvg;
		this.trades = trades;
		this.low = low;
		this.high = high;
		this.open = open;
	}

	public BigDecimal getTodaysVolume() {
		return volume[0];
	}

	public BigDecimal get24HourVolume() {
		return volume[1];
	}

	public BigDecimal getTodaysVolumeAvg() {
		return volumeAvg[0];
	}

	public BigDecimal get24HourVolumeAvg() {
		return volumeAvg[1];
	}

	public BigDecimal getTodaysNumTrades() {
		return trades[0];
	}

	public BigDecimal get24HourNumTrades() {
		return trades[1];
	}

	public BigDecimal getTodaysLow() {
		return low[0];
	}

	public BigDecimal get24HourLow() {
		return low[1];
	}

	public BigDecimal getTodaysHigh() {
		return high[0];
	}

	public BigDecimal get24HourHigh() {
		return high[1];
	}

	@Override
	public String toString() {
		return "KrakenTicker [ask="
				+ ask
				+ ", bid="
				+ bid
				+ ", close="
				+ close
				+ ", volume="
				+ Arrays.toString(volume)
				+ ", volumeAvg="
				+ Arrays.toString(volumeAvg)
				+ ", trades="
				+ Arrays.toString(trades)
				+ ", low="
				+ Arrays.toString(low)
				+ ", high="
				+ Arrays.toString(high)
				+ ", open="
				+ open
				+ "]";
	}
}