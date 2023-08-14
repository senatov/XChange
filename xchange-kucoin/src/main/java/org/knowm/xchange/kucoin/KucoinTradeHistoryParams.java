package org.knowm.xchange.kucoin;

import lombok.Data;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.FundingRecord.Type;
import org.knowm.xchange.service.trade.params.HistoryParamsFundingType;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParamNextPageCursor;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;

import java.util.Date;

@Data
public class KucoinTradeHistoryParams
		implements TradeHistoryParams,
		TradeHistoryParamCurrencyPair,
		HistoryParamsFundingType,
		TradeHistoryParamsTimeSpan,
		TradeHistoryParamNextPageCursor {

	private Date startTime;
	private Date endTime;
	private Type type;
	private CurrencyPair currencyPair;
	private String nextPageCursor;
}