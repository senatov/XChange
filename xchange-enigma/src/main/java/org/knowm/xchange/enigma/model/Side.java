package org.knowm.xchange.enigma.model;

public enum Side {
	BUY(1),
	SELL(2);

	private final int value;

	Side(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
}