package info.bitrich.xchangestream.coincheck.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckPair;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
@Jacksonized
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({"id", "pair", "price", "amount", "orderType"})
public class CoincheckStreamingTrade {
	String id;
	CoincheckPair pair;
	BigDecimal price;
	BigDecimal amount;
	String orderType;
}