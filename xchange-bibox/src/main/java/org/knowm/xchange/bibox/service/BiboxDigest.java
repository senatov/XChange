package org.knowm.xchange.bibox.service;

import org.knowm.xchange.bibox.BiboxAuthenticated;
import org.knowm.xchange.service.BaseParamsDigest;
import org.knowm.xchange.utils.DigestUtils;
import si.mazi.rescu.RestInvocation;

import javax.ws.rs.FormParam;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class BiboxDigest extends BaseParamsDigest {

	/**
	 * Constructor
	 *
	 * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded
	 * key is invalid).
	 */
	private BiboxDigest(String secretKey) {
		super(secretKey, HMAC_MD5);
	}

	public static BiboxDigest createInstance(String secretKey) {
		return secretKey == null ? null : new BiboxDigest(secretKey);
	}

	@Override
	public String digestParams(RestInvocation restInvocation) {
		String cmds =
				(String) restInvocation.getParamValue(FormParam.class, BiboxAuthenticated.FORM_CMDS);
		try {
			return DigestUtils.bytesToHex(getMac().doFinal(cmds.getBytes(StandardCharsets.UTF_8))).toLowerCase();
		} catch (IllegalStateException e1) {
			throw new RuntimeException(e1.getMessage());
		}
	}
}