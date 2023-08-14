package org.knowm.xchange.bittrex.dto.withdrawal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BittrexWithdrawal {
	private String id;
	private String currencySymbol;
	private BigDecimal quantity;
	private String cryptoAddress;
	private String cryptoAddressTag;
	private String fundsTransferMethodId;
	private BigDecimal txCost;
	private String txId;
	private Status status;
	private Date createdAt;
	private Date completedAt;
	private String clientWithdrawalId;
	private Target target;
	private String accountId;
	private Error error;
}