package org.knowm.xchange.simulated;

import org.knowm.xchange.ExchangeSpecification;

import java.io.IOException;

/**
 * Listener which is called every time the {@link SimulatedExchange} performs an operation.
 * <p>Pass instances to {@link ExchangeSpecification#getExchangeSpecificParametersItem(String)}
 * using the parameter name {@link SimulatedExchange#ON_OPERATION_PARAM} to have them called back.
 * <p>See {@link RandomExceptionThrower} for an example implementation.
 *
 * @author Graham Crockford
 */
public interface SimulatedExchangeOperationListener {

	/**
	 * Called every time
	 */
	void onSimulatedExchangeOperation() throws IOException;
}