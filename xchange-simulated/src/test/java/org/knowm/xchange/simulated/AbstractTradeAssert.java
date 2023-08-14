package org.knowm.xchange.simulated;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.util.Objects;
import org.knowm.xchange.dto.marketdata.Trade;

/**
 * Abstract base class for {@link Trade} specific assertions - Generated by
 * CustomAssertionGenerator.
 */
@javax.annotation.Generated(value = "assertj-assertions-generator")
abstract class AbstractTradeAssert<S extends AbstractTradeAssert<S, A>, A extends Trade>
		extends AbstractObjectAssert<S, A> {

	/**
	 * Creates a new <code>{@link AbstractTradeAssert}</code> to make assertions on actual Trade.
	 *
	 * @param actual the Trade we want to make assertions on.
	 */
	protected AbstractTradeAssert(A actual, Class<S> selfType) {
		super(actual, selfType);
	}

	/**
	 * Verifies that the actual Trade's currencyPair is equal to the given one.
	 *
	 * @param currencyPair the given currencyPair to compare the actual Trade's currencyPair to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's currencyPair is not equal to the given one.
	 */
	public S hasCurrencyPair(org.knowm.xchange.currency.CurrencyPair currencyPair) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage =
				"\nExpecting currencyPair of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		org.knowm.xchange.currency.CurrencyPair actualCurrencyPair = actual.getCurrencyPair();
		if (!Objects.areEqual(actualCurrencyPair, currencyPair)) {
			failWithMessage(assertjErrorMessage, actual, currencyPair, actualCurrencyPair);
		}
		// return the current assertion for method chaining
		return myself;
	}

	/**
	 * Verifies that the actual Trade's id is equal to the given one.
	 *
	 * @param id the given id to compare the actual Trade's id to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's id is not equal to the given one.
	 */
	public S hasId(String id) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage = "\nExpecting id of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		String actualId = actual.getId();
		if (!Objects.areEqual(actualId, id)) {
			failWithMessage(assertjErrorMessage, actual, id, actualId);
		}
		// return the current assertion for method chaining
		return myself;
	}

	public S hasId() {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage = "\nExpecting id to not be null";
		if (actual.getId() == null) {
			failWithMessage(assertjErrorMessage);
		}
		return myself;
	}

	/**
	 * Verifies that the actual Trade's makerOrderId is equal to the given one.
	 *
	 * @param makerOrderId the given makerOrderId to compare the actual Trade's makerOrderId to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's makerOrderId is not equal to the given one.
	 */
	public S hasMakerOrderId(String makerOrderId) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage =
				"\nExpecting makerOrderId of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		String actualMakerOrderId = actual.getMakerOrderId();
		if (!Objects.areEqual(actualMakerOrderId, makerOrderId)) {
			failWithMessage(assertjErrorMessage, actual, makerOrderId, actualMakerOrderId);
		}
		// return the current assertion for method chaining
		return myself;
	}

	/**
	 * Verifies that the actual Trade's originalAmount is equal to the given one.
	 *
	 * @param originalAmount the given originalAmount to compare the actual Trade's originalAmount to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's originalAmount is not equal to the given one.
	 */
	public S hasOriginalAmount(java.math.BigDecimal originalAmount) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage =
				"\nExpecting originalAmount of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		java.math.BigDecimal actualOriginalAmount = actual.getOriginalAmount();
		if (!Objects.areEqual(actualOriginalAmount, originalAmount)) {
			failWithMessage(assertjErrorMessage, actual, originalAmount, actualOriginalAmount);
		}
		// return the current assertion for method chaining
		return myself;
	}

	/**
	 * Verifies that the actual Trade's price is equal to the given one.
	 *
	 * @param price the given price to compare the actual Trade's price to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's price is not equal to the given one.
	 */
	public S hasPrice(java.math.BigDecimal price) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage = "\nExpecting price of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		java.math.BigDecimal actualPrice = actual.getPrice();
		if (!Objects.areEqual(actualPrice, price)) {
			failWithMessage(assertjErrorMessage, actual, price, actualPrice);
		}
		// return the current assertion for method chaining
		return myself;
	}

	/**
	 * Verifies that the actual Trade's takerOrderId is equal to the given one.
	 *
	 * @param takerOrderId the given takerOrderId to compare the actual Trade's takerOrderId to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's takerOrderId is not equal to the given one.
	 */
	public S hasTakerOrderId(String takerOrderId) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage =
				"\nExpecting takerOrderId of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		String actualTakerOrderId = actual.getTakerOrderId();
		if (!Objects.areEqual(actualTakerOrderId, takerOrderId)) {
			failWithMessage(assertjErrorMessage, actual, takerOrderId, actualTakerOrderId);
		}
		// return the current assertion for method chaining
		return myself;
	}

	/**
	 * Verifies that the actual Trade's timestamp is equal to the given one.
	 *
	 * @param timestamp the given timestamp to compare the actual Trade's timestamp to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's timestamp is not equal to the given one.
	 */
	public S hasTimestamp(java.util.Date timestamp) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage =
				"\nExpecting timestamp of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		java.util.Date actualTimestamp = actual.getTimestamp();
		if (!Objects.areEqual(actualTimestamp, timestamp)) {
			failWithMessage(assertjErrorMessage, actual, timestamp, actualTimestamp);
		}
		// return the current assertion for method chaining
		return myself;
	}

	/**
	 * Verifies that the actual Trade's type is equal to the given one.
	 *
	 * @param type the given type to compare the actual Trade's type to.
	 * @return this assertion object.
	 * @throws AssertionError - if the actual Trade's type is not equal to the given one.
	 */
	public S hasType(org.knowm.xchange.dto.Order.OrderType type) {
		// check that actual Trade we want to make assertions on is not null.
		isNotNull();
		// overrides the default error message with a more explicit one
		String assertjErrorMessage = "\nExpecting type of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
		// null safe check
		org.knowm.xchange.dto.Order.OrderType actualType = actual.getType();
		if (!Objects.areEqual(actualType, type)) {
			failWithMessage(assertjErrorMessage, actual, type, actualType);
		}
		// return the current assertion for method chaining
		return myself;
	}
}