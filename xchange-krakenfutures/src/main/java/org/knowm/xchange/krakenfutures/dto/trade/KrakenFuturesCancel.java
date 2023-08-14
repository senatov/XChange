package org.knowm.xchange.krakenfutures.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.knowm.xchange.krakenfutures.dto.KrakenFuturesResult;

import java.util.Date;

/**
 * @author Jean-Christophe Laruelle
 */
@Getter
@ToString
public class KrakenFuturesCancel extends KrakenFuturesResult {

	private final Date serverTime;
	private final KrakenFuturesCancelStatus cancelStatus;

	public KrakenFuturesCancel(
			@JsonProperty("result") String result,
			@JsonProperty("serverTime") Date serverTime,
			@JsonProperty("cancelStatus") KrakenFuturesCancelStatus cancelStatus,
			@JsonProperty("error") String error) {
		super(result, error);
		this.serverTime = serverTime;
		this.cancelStatus = cancelStatus;
	}
}