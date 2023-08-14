package org.knowm.xchange.utils.timestamp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnixTimestampFactoryTest {

	private static final long MILLIS_IN_SECOND = 1000L;

	@Test
	public void testCreateValueShouldReturnCurrentValidSecondsValue() {
		long beforeUnixTimestamp = System.currentTimeMillis() / MILLIS_IN_SECOND;
		final Long unixTimestamp = UnixTimestampFactory.INSTANCE.createValue();
		long afterUnixTimestamp = System.currentTimeMillis() / MILLIS_IN_SECOND;
		assertThat(unixTimestamp)
				.isGreaterThanOrEqualTo(beforeUnixTimestamp)
				.isLessThanOrEqualTo(afterUnixTimestamp);
	}
}