package org.knowm.xchange.btcturk.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author mertguner
 */
public class BTCTurkDigest extends BaseParamsDigest {

	private final byte[] SecretKey;

	private BTCTurkDigest(String secretKey) {
		super(secretKey, HMAC_SHA_256);
		SecretKey = Base64.getDecoder().decode(secretKey);
	}

	public static BTCTurkDigest createInstance(String secretKey, String apiKey) {
		return secretKey == null ? null : new BTCTurkDigest(secretKey);
	}

	@Override
	public String digestParams(RestInvocation restInvocation) {
		String ApiKey = restInvocation.getHttpHeadersFromParams().get("X-PCK");
		String timestamp = restInvocation.getHttpHeadersFromParams().get("X-Stamp");
		return generateHash(ApiKey, timestamp);
	}

	private String generateHash(String ApiKey, String timestamp) {
		Mac sha256_HMAC = null;
		String message = ApiKey + timestamp;
		try {
			sha256_HMAC = Mac.getInstance(HMAC_SHA_256);
			SecretKeySpec keySpec = new SecretKeySpec(SecretKey, HMAC_SHA_256);
			sha256_HMAC.init(keySpec);
			return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return "";
	}
}