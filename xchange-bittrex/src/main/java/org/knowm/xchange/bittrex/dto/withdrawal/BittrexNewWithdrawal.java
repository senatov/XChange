package org.knowm.xchange.bittrex.dto.withdrawal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BittrexNewWithdrawal {
	private String currencySymbol;
	private BigDecimal quantity;
	private String cryptoAddress;
	private String cryptoAddressTag;
	private String fundsTransferMethodId;
	private String clientWithdrawalId;
}