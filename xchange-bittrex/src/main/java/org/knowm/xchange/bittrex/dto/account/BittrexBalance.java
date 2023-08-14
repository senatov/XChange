package org.knowm.xchange.bittrex.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.knowm.xchange.currency.Currency;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BittrexBalance {
	private Currency currencySymbol;
	private BigDecimal total;
	private BigDecimal available;
	private Date updatedAt;
}