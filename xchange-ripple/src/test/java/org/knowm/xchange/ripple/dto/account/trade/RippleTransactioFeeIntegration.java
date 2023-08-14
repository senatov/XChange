package org.knowm.xchange.ripple.dto.account.trade;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ripple.RippleExchange;
import org.knowm.xchange.ripple.service.RippleTradeService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class RippleTransactioFeeIntegration {

	@Test
	public void getTransactionFeeTest() {
		final Exchange exchange = ExchangeFactory.INSTANCE.createExchange(RippleExchange.class);
		final RippleTradeService tradeService = (RippleTradeService) exchange.getTradeService();
		final BigDecimal transactionFee = tradeService.getTransactionFee();
		assertThat(transactionFee).isGreaterThan(BigDecimal.ZERO);
		System.out.printf("Ripple network transaction fee is currently %s XRP%n", transactionFee);
	}
}