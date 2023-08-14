package org.knowm.xchange.zaif.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.currency.CurrencyPair;

public class ZaifMarket {

	@JsonProperty("name")
	private CurrencyPair name;

	@Override
	public String toString() {
		return "ZaifMarket [name = " + this.getName() + "]";
	}

	@JsonProperty("name")
	public CurrencyPair getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(CurrencyPair name) {
		this.name = name;
	}
}