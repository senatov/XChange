package org.knowm.xchange.kucoin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistOrdersResponse {

	private String symbol;

	private String side;
	@JsonProperty("dealPrice")
	private BigDecimal price;
	// price * amount (i.e. volume of this trade in counter ccy)
	@JsonProperty("dealValue")
	private BigDecimal value;
	private BigDecimal amount;
	private BigDecimal fee;
	@JsonProperty("createdAt")
	private long createdAtSeconds;
	private String id;

	public String getSide() {
		return this.side == null ? null : this.side.toLowerCase();
	}

	public Date getTradeCreatedAt() {
		return new Date(createdAtSeconds * 1000);
	}
}