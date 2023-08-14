package org.knowm.xchange.bitmex.dto.trade;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.knowm.xchange.bitmex.AbstractHttpResponseAware;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BitmexCancelAll extends AbstractHttpResponseAware {
	private Date now;
	private Date cancelTime;
}