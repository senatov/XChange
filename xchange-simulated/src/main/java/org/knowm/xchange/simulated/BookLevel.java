package org.knowm.xchange.simulated;

import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Data
final class BookLevel {
	private final BigDecimal price;
	private final List<BookOrder> orders = new LinkedList<>();
}