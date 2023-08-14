package org.knowm.xchange.bankera.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankeraUserInfo {
	private final BankeraUser user;

	public BankeraUserInfo(@JsonProperty("user") BankeraUser user) {
		this.user = user;
	}

	public BankeraUser getUser() {
		return user;
	}
}