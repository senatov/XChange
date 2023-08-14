package org.knowm.xchange.bitfinex.v1;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import java.math.BigInteger;
import java.util.Base64;

public class BitfinexDigest extends BaseParamsDigest {

	/**
	 * Constructor
	 *
	 * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded
	 * key is invalid).
	 */
	private BitfinexDigest(String secretKeyBase64) {
		super(secretKeyBase64, HMAC_SHA_384);
	}

	public static BitfinexDigest createInstance(String secretKeyBase64) {
		return secretKeyBase64 == null ? null : new BitfinexDigest(secretKeyBase64);
	}

	@Override
	public String digestParams(RestInvocation restInvocation) {
		String postBody = restInvocation.getRequestBody();
		Mac mac = getMac();
		mac.update(Base64.getEncoder().encodeToString(postBody.getBytes()).getBytes());
		return String.format("%096x", new BigInteger(1, mac.doFinal()));
	}
}