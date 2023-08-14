package org.knowm.xchange.kucoin.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class WebsocketResponse {
	private String token;
	private List<InstanceServer> instanceServers;

	@Data
	public static class InstanceServer {
		private String endpoint;
		private boolean encrypt;
		private String protocol;
		private int pingInterval;
		private int pingTimeout;
	}
}