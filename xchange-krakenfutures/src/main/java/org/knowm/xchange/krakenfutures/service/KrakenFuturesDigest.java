package org.knowm.xchange.krakenfutures.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Jean-Christophe Laruelle
 */
public class KrakenFuturesDigest extends BaseParamsDigest {

	/**
	 * Constructor
	 *
	 * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded
	 * key is invalid).
	 */
	private KrakenFuturesDigest(byte[] secretKeyBase64) {
		super(secretKeyBase64, HMAC_SHA_512);
	}

	public static KrakenFuturesDigest createInstance(String secretKeyBase64) {
		if (secretKeyBase64 != null) {
			return new KrakenFuturesDigest(Base64.getDecoder().decode(secretKeyBase64.getBytes()));
		} else {
			return null;
		}
	}

	@Override
	public String digestParams(RestInvocation restInvocation) {
		String message = URLDecoder.decode(
				restInvocation.getParamsMap().get(QueryParam.class).asQueryString(),
				StandardCharsets.UTF_8)
				+ restInvocation.getParamValue(HeaderParam.class, "Nonce").toString()
				+ restInvocation.getPath();
		return signMessage(message);


	}

	public String signMessage(String message) {
		MessageDigest sha256;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(
					"Illegal algorithm (SHA-256) for post body digest. Check the implementation.");
		}
		sha256.update(message.getBytes());
		Mac mac512 = getMac();
		mac512.update(sha256.digest());
		return Base64.getEncoder().encodeToString(mac512.doFinal()).trim();
	}
}