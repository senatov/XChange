package org.knowm.xchange.examples.enigma;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.enigma.EnigmaExchange;
import org.knowm.xchange.enigma.service.EnigmaAccountService;

import java.io.IOException;

public class EnigmaDemoUtils {

	private static final String username = "iSemyonova";
	private static final String password = "irinaEnigmaSecuritiesRestApi123!";
	private static final String infra = "dev";

	public static Exchange createExchange() {
		EnigmaExchange enigmaExchange = new EnigmaExchange();
		ExchangeSpecification exchangeSpec = enigmaExchange.getDefaultExchangeSpecification();
		exchangeSpec.setExchangeSpecificParametersItem("infra", infra);
		exchangeSpec.setUserName(username);
		exchangeSpec.setPassword(password);
		enigmaExchange.applySpecification(exchangeSpec);
		try {
			((EnigmaAccountService) enigmaExchange.getAccountService()).login();
		} catch (IOException e) {
			throw new RuntimeException("Login exception", e);
		}
		return enigmaExchange;
	}
}