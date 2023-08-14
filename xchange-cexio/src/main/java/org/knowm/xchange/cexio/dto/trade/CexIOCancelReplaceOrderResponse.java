package org.knowm.xchange.cexio.dto.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CexIOCancelReplaceOrderResponse {

	private Boolean complete;
	private BigDecimal price;
	private BigDecimal amount;
	private Date time;
	private String type;
	private String id;
	private BigDecimal pending;

	private String error;

	public Boolean getComplete() {
		return complete;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Date getTime() {
		return time;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getPending() {
		return pending;
	}

	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		String buffer = "{" + "id='" + id + '\'' +
				", complete=" + complete +
				", price=" + price +
				", amount=" + amount +
				", time=" + time +
				", type='" + type + '\'' +
				", pending=" + pending +
				", error=" + error +
				'}';
		return buffer;
	}
}