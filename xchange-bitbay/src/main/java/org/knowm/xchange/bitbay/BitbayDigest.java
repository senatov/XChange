package org.knowm.xchange.bitbay;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * @author kfonal
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
		String postBody = restInvocation.getRequestBody();
		Mac mac = getMac();
		mac.update(postBody.getBytes(StandardCharsets.UTF_8));
		return String.format("%0128x", new BigInteger(1, mac.doFinal()));
	}
}