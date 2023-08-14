package com.knowm.xchange.serum.structures;

import java.io.IOException;
import java.util.Base64;

public interface StructDecoder<T> {

	default T decode(final String data) throws IOException {
		return decode(Base64.getDecoder().decode(data));
	}

	T decode(final byte[] bytes) throws IOException;
}