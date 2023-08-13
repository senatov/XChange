package org.knowm.xchange.kraken.dto.marketdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicOrder.KrakenOrderDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

@JsonDeserialize(using = KrakenOrderDeserializer.class)
public record KrakenPublicOrder(BigDecimal price, BigDecimal volume, long timestamp) {


	@Override
	public String toString() {
		return "KrakenOrder [price=" + price + ", volume=" + volume + ", timestamp=" + timestamp + "]";
	}

	static class KrakenOrderDeserializer extends JsonDeserializer<KrakenPublicOrder> {

		@Override
		public KrakenPublicOrder deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException {
			ObjectCodec oc = jsonParser.getCodec();
			JsonNode node = oc.readTree(jsonParser);
			if (node.isArray()) {
				BigDecimal price = new BigDecimal(node.path(0).asText());
				BigDecimal volume = new BigDecimal(node.path(1).asText());
				long timestamp = node.path(2).asLong();
				return new KrakenPublicOrder(price, volume, timestamp);
			}
			return null;
		}
	}
}