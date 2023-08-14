package org.knowm.xchange.livecoin.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.livecoin.dto.LivecoinBaseResponse;

import java.util.ArrayList;
import java.util.List;

public class LivecoinRestrictions extends LivecoinBaseResponse {

	private List<LivecoinRestriction> restrictions = new ArrayList<>();

	public LivecoinRestrictions(
			@JsonProperty("success") Boolean success,
			@JsonProperty("restrictions") List<LivecoinRestriction> restrictions) {
		super(success);
		this.restrictions = restrictions;
	}

	public List<LivecoinRestriction> getRestrictions() {
		return restrictions;
	}
}