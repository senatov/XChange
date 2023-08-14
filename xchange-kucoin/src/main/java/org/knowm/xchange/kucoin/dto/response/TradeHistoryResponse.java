/**
 * Copyright 2019 Mek Global Limited.
 */
package org.knowm.xchange.kucoin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/** Created by chenshiwei on 2019/1/18. */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeHistoryResponse {

	private String sequence;

	private BigDecimal price;

	private BigDecimal size;

	private String side;

	private long time;
}