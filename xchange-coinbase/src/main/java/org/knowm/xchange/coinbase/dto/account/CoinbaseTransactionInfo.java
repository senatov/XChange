package org.knowm.xchange.coinbase.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.knowm.xchange.coinbase.dto.account.CoinbaseTransaction.CoinbaseTransactionStatus;
import org.knowm.xchange.coinbase.dto.marketdata.CoinbaseMoney;

import java.util.Date;

/**
 * @author jamespedwards42
 */
public interface CoinbaseTransactionInfo {

	@JsonIgnore
	String getId();

	@JsonIgnore
	Date getCreatedAt();

	@JsonIgnore
	CoinbaseMoney getAmount();

	@JsonIgnore
	boolean isRequest();

	@JsonIgnore
	CoinbaseTransactionStatus getStatus();

	@JsonIgnore
	CoinbaseUser getSender();

	@JsonIgnore
	CoinbaseUser getRecipient();

	@JsonIgnore
	String getRecipientAddress();

	String getNotes();

	@JsonIgnore
	String getTransactionHash();

	@JsonIgnore
	String getIdempotencyKey();
}