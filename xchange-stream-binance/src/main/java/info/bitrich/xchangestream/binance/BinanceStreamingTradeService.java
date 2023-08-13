package info.bitrich.xchangestream.binance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.bitrich.xchangestream.binance.dto.ExecutionReportBinanceUserTransaction;
import info.bitrich.xchangestream.core.StreamingTradeService;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.derivative.FuturesContract;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.ExchangeSecurityException;
import org.knowm.xchange.instrument.Instrument;

import java.io.IOException;

import static info.bitrich.xchangestream.binance.dto.BaseBinanceWebSocketTransaction.BinanceWebSocketTypes.EXECUTION_REPORT;
import static info.bitrich.xchangestream.binance.dto.ExecutionReportBinanceUserTransaction.ExecutionType.REJECTED;
import static info.bitrich.xchangestream.binance.dto.ExecutionReportBinanceUserTransaction.ExecutionType.TRADE;

public class BinanceStreamingTradeService implements StreamingTradeService {

	private final Subject<ExecutionReportBinanceUserTransaction> executionReportsPublisher =
			PublishSubject.<ExecutionReportBinanceUserTransaction>create().toSerialized();
	private final ObjectMapper mapper = StreamingObjectMapperHelper.getObjectMapper();
	private volatile Disposable executionReports;
	private volatile BinanceUserDataStreamingService binanceUserDataStreamingService;

	public BinanceStreamingTradeService(
			BinanceUserDataStreamingService binanceUserDataStreamingService) {
		this.binanceUserDataStreamingService = binanceUserDataStreamingService;
	}

	@Override
	public Observable<Order> getOrderChanges(CurrencyPair currencyPair, Object... args) {
		return getOrderChanges(false).filter(oc -> currencyPair.equals(oc.getInstrument()));
	}

	public Observable<Order> getOrderChanges(boolean isFuture) {
		return getRawExecutionReports()
				.filter(r -> !r.getExecutionType().equals(REJECTED))
				.map(binanceExec -> binanceExec.toOrder(isFuture));
	}

	public Observable<ExecutionReportBinanceUserTransaction> getRawExecutionReports() {
		if (binanceUserDataStreamingService == null || !binanceUserDataStreamingService.isSocketOpen())
			throw new ExchangeSecurityException("Not authenticated");
		return executionReportsPublisher;
	}

	@Override
	public Observable<Order> getOrderChanges(Instrument instrument, Object... args) {
		return getOrderChanges(instrument instanceof FuturesContract).filter(oc -> instrument.equals(oc.getInstrument()));
	}

	@Override
	public Observable<UserTrade> getUserTrades(CurrencyPair currencyPair, Object... args) {
		return getUserTrades(false).filter(t -> t.getInstrument().equals(currencyPair));
	}

	@Override
	public Observable<UserTrade> getUserTrades(Instrument instrument, Object... args) {
		return getUserTrades(instrument instanceof FuturesContract).filter(t -> t.getInstrument().equals(instrument));
	}

	public Observable<UserTrade> getUserTrades(boolean isFuture) {
		return getRawExecutionReports()
				.filter(r -> r.getExecutionType().equals(TRADE))
				.map(binanceExec -> binanceExec.toUserTrade(isFuture));
	}

	/**
	 * User data subscriptions may have to persist across multiple socket connections to different
	 * URLs and therefore must act in a publisher fashion so that subscribers get an uninterrupted
	 * stream.
	 */
	void setUserDataStreamingService(
			BinanceUserDataStreamingService binanceUserDataStreamingService) {
		if (executionReports != null && !executionReports.isDisposed())
			executionReports.dispose();
		this.binanceUserDataStreamingService = binanceUserDataStreamingService;
		openSubscriptions();
	}

	/**
	 * Registers subsriptions with the streaming service for the given products.
	 */
	public void openSubscriptions() {
		if (binanceUserDataStreamingService != null) {
			executionReports =
					binanceUserDataStreamingService
							.subscribeChannel(EXECUTION_REPORT)
							.map(this::executionReport)
							.subscribe(executionReportsPublisher::onNext);
		}
	}

	private ExecutionReportBinanceUserTransaction executionReport(JsonNode json) {
		try {
			return mapper.treeToValue(json, ExecutionReportBinanceUserTransaction.class);
		} catch (IOException e) {
			throw new ExchangeException("Unable to parse execution report", e);
		}
	}
}