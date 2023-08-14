package org.knowm.xchange.krakenfutures.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Panchen
 */
@Getter
@ToString
public class KrakenFuturesCancelStatus {

	private final Date receivedTime;
	private final String status;

	public KrakenFuturesCancelStatus(
			@JsonProperty("receivedTime") Date receivedTime, @JsonProperty("status") String status) {
		this.receivedTime = receivedTime;
		this.status = status;
	}
}