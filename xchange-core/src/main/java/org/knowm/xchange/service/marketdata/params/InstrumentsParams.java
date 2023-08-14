package org.knowm.xchange.service.marketdata.params;

import org.knowm.xchange.instrument.Instrument;

import java.util.Collection;

public interface InstrumentsParams extends Params {
	Collection<Instrument> getInstruments();
}