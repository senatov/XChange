package org.knowm.xchange.ripple.dto.account;

import org.knowm.xchange.ripple.dto.RippleException;

import java.io.IOException;
import java.math.BigDecimal;

public interface ITransferFeeSource {
	BigDecimal getTransferFeeRate(String address) throws RippleException, IOException;
}