package info.bitrich.xchangestream.binance;

import com.fasterxml.jackson.databind.JsonNode;
import info.bitrich.xchangestream.binance.dto.BaseBinanceWebSocketTransaction.BinanceWebSocketTypes;
import info.bitrich.xchangestream.service.netty.JsonNettyStreamingService;
import info.bitrich.xchangestream.service.netty.WebSocketClientCompressionAllowClientNoContextAndServerNoContextHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandler;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinanceUserDataStreamingService extends JsonNettyStreamingService {

	private static final Logger LOG = LoggerFactory.getLogger(BinanceUserDataStreamingService.class);

	private BinanceUserDataStreamingService(String url) {
		super(url, Integer.MAX_VALUE);
	}

	public static BinanceUserDataStreamingService create(String baseUri, String listenKey) {
		return new BinanceUserDataStreamingService(baseUri + "ws/" + listenKey);
	}

	public Observable<JsonNode> subscribeChannel(BinanceWebSocketTypes eventType) {
		return super.subscribeChannel(eventType.getSerializedValue());
	}

	@Override
	public void messageHandler(String message) {
		LOG.debug("Received message: {}", message);
		super.messageHandler(message);
	}

	@Override
	protected String getChannelNameFromMessage(JsonNode message) {
		return message.get("e").asText();
	}

	@Override
	public String getSubscribeMessage(String channelName, Object... args) {
		// No op. Disconnecting from the web socket will cancel subscriptions.
		return null;
	}

	@Override
	public String getUnsubscribeMessage(String channelName, Object... args) {
		// No op. Disconnecting from the web socket will cancel subscriptions.
		return null;
	}

	@Override
	public void sendMessage(String message) {
		// Subscriptions are made upon connection - no messages are sent.
	}

	@Override
	protected void handleMessage(JsonNode message) {
		try {
			super.handleMessage(message);
		} catch (Exception e) {
			LOG.error("Error handling message: " + message, e);
		}
	}

	@Override
	protected WebSocketClientExtensionHandler getWebSocketClientExtensionHandler() {
		return WebSocketClientCompressionAllowClientNoContextAndServerNoContextHandler.INSTANCE;
	}
}