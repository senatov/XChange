package org.knowm.xchange.bitbay.v3;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author walec51
 */
public class BitbayDigest extends BaseParamsDigest {

	private BitbayDigest(String secretKeyBase64) throws IllegalArgumentException {
		super(secretKeyBase64, HMAC_SHA_512);
	}

	public static BitbayDigest createInstance(String secretKeyBase64) {
		return secretKeyBase64 == null ? null : new BitbayDigest(secretKeyBase64);
	}

	@Override
	public String digestParams(RestInvocation restInvocation) {
		Map<String, String> headers = restInvocation.getHttpHeadersFromParams();
		String apiKey = headers.get("API-Key");
		Long requestTimestamp = Long.parseLong(headers.get("Request-Timestamp"));
		String hashContent = apiKey + requestTimestamp;
		Mac mac = getMac();
		mac.update(hashContent.getBytes(StandardCharsets.UTF_8));
		return String.format("%0128x", new BigInteger(1, mac.doFinal()));
	}
}