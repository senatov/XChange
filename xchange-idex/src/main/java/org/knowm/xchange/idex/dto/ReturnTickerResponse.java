package org.knowm.xchange.idex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.idex.annotations.ApiModelProperty;

import java.util.Objects;

public class ReturnTickerResponse {

	private String last = "";
	private String high = "";
	private String low = "";
	private String lowestAsk = "";
	private String highestBid = "";
	private String percentChange = "";
	private String baseVolume = "";
	private String quoteVolume = "";

	/**
	 *
	 */
	public ReturnTickerResponse last(String last) {
		this.last = last;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("last")
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	/**
	 *
	 */
	public ReturnTickerResponse high(String high) {
		this.high = high;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("high")
	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	/**
	 *
	 */
	public ReturnTickerResponse low(String low) {
		this.low = low;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("low")
	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	/**
	 *
	 */
	public ReturnTickerResponse lowestAsk(String lowestAsk) {
		this.lowestAsk = lowestAsk;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("lowestAsk")
	public String getLowestAsk() {
		return lowestAsk;
	}

	public void setLowestAsk(String lowestAsk) {
		this.lowestAsk = lowestAsk;
	}

	/**
	 *
	 */
	public ReturnTickerResponse highestBid(String highestBid) {
		this.highestBid = highestBid;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("highestBid")
	public String getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(String highestBid) {
		this.highestBid = highestBid;
	}

	/**
	 *
	 */
	public ReturnTickerResponse percentChange(String percentChange) {
		this.percentChange = percentChange;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("percentChange")
	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	/**
	 *
	 */
	public ReturnTickerResponse baseVolume(String baseVolume) {
		this.baseVolume = baseVolume;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("baseVolume")
	public String getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(String baseVolume) {
		this.baseVolume = baseVolume;
	}

	/**
	 *
	 */
	public ReturnTickerResponse quoteVolume(String quoteVolume) {
		this.quoteVolume = quoteVolume;
		return this;
	}

	@ApiModelProperty("")
	@JsonProperty("quoteVolume")
	public String getQuoteVolume() {
		return quoteVolume;
	}

	public void setQuoteVolume(String quoteVolume) {
		this.quoteVolume = quoteVolume;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				last, high, low, lowestAsk, highestBid, percentChange, baseVolume, quoteVolume);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReturnTickerResponse returnTickerResponse = (ReturnTickerResponse) o;
		return Objects.equals(last, returnTickerResponse.last)
				&& Objects.equals(high, returnTickerResponse.high)
				&& Objects.equals(low, returnTickerResponse.low)
				&& Objects.equals(lowestAsk, returnTickerResponse.lowestAsk)
				&& Objects.equals(highestBid, returnTickerResponse.highestBid)
				&& Objects.equals(percentChange, returnTickerResponse.percentChange)
				&& Objects.equals(baseVolume, returnTickerResponse.baseVolume)
				&& Objects.equals(quoteVolume, returnTickerResponse.quoteVolume);
	}

	@Override
	public String toString() {
		String sb = "class ReturnTickerResponse {\n" +
				"    last: " + toIndentedString(last) + "\n" +
				"    high: " + toIndentedString(high) + "\n" +
				"    low: " + toIndentedString(low) + "\n" +
				"    lowestAsk: " + toIndentedString(lowestAsk) + "\n" +
				"    highestBid: " + toIndentedString(highestBid) + "\n" +
				"    percentChange: " + toIndentedString(percentChange) + "\n" +
				"    baseVolume: " + toIndentedString(baseVolume) + "\n" +
				"    quoteVolume: " + toIndentedString(quoteVolume) + "\n" +
				"}";
		return sb;
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}