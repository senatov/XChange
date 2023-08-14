package org.knowm.xchange.bitbay.v3.dto.trade;

import lombok.Data;

import java.util.List;

@Data
public class BitbayBalancesHistoryQuery {

	private String limit;
	private String offset;
	private List<String> types;
	private Long fromTime;
	private Long toTime;
}