package org.knowm.xchange.ftx;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExchangeInitIntegration {

	@Test
	public void ftxInitializationTest() {
		Exchange ftx = ExchangeFactory.INSTANCE.createExchange(FtxExchange.class);
		assertThat(ftx.getExchangeInstruments().isEmpty()).isFalse();
		assertThat(ftx.getExchangeInstruments().isEmpty()).isFalse();
		assertThat(ftx.getExchangeMetaData().getInstruments().isEmpty()).isFalse();
	}
}