package org.knowm.xchange.bitfinex.service;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestInvocation;

import java.util.Base64;

public class BitfinexPayloadDigest implements ParamsDigest {

	@Override
	public synchronized String digestParams(RestInvocation restInvocation) {
		String postBody = restInvocation.getRequestBody();
		return Base64.getEncoder().encodeToString(postBody.getBytes());
	}
}