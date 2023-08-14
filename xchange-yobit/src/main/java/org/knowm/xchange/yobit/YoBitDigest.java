package org.knowm.xchange.yobit;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.Params;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import javax.ws.rs.FormParam;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class YoBitDigest extends BaseParamsDigest {

	private YoBitDigest(String secretKeyBase64, String apiKey) throws IOException {
		super(secretKeyBase64, HMAC_SHA_512);
	}

	public static YoBitDigest createInstance(String secretKeyBase64, String apiKey) {
		try {
			String checkedSecretKeyBase64 =
					secretKeyBase64 != null ? secretKeyBase64 : getRandomSecretKey();
			return new YoBitDigest(checkedSecretKeyBase64, apiKey);
		} catch (Exception e) {
			throw new IllegalStateException("cannot create digest", e);
		}
	}

	private static String getRandomSecretKey() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		return new String(array, StandardCharsets.UTF_8);
	}

	@Override
	public String digestParams(RestInvocation restInvocation) {
		Params params = restInvocation.getParamsMap().get(FormParam.class);
		String queryString = params.asQueryString();
		Mac mac = getMac();
		byte[] source = mac.doFinal(queryString.getBytes());
		return String.format("%0128x", new BigInteger(1, source));
	}
}