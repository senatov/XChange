package org.knowm.xchange.coincheck.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckPair;

import java.io.IOException;

public class CoincheckPairDeserializer extends JsonDeserializer<CoincheckPair> {
	@Override
	public CoincheckPair deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		return CoincheckPair.stringToPair(p.getValueAsString());
	}
}