package org.knowm.xchange.coinmate.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.coinmate.Coinmate;
import org.knowm.xchange.dto.meta.ExchangeMetaData;

import java.io.IOException;

public class CoinmateMetadataServiceRaw extends CoinmateBaseService {
	private final Coinmate coinmate;

	public CoinmateMetadataServiceRaw(Exchange exchange) {
		super(exchange);
		this.coinmate =
				ExchangeRestProxyBuilder.forInterface(Coinmate.class, exchange.getExchangeSpecification())
						.build();
	}

	public ExchangeMetaData getMetadata() throws IOException {
		ExchangeMetaData metaData = coinmate.getMetadata();
		return metaData;
	}
}