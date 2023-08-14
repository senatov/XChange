package org.knowm.xchange.coincheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Objects;

public class CoincheckTestUtil {
	@SneakyThrows
	public static <T> T load(String filename, Class<T> clazz) {
		return createObjectMapper().readValue(load(filename), clazz);
	}

	public static InputStream load(String filename) {
		String dir = CoincheckTestUtil.class.getPackage().getName().replace('.', '/');
		String path = '/' + dir + '/' + filename;
		return Objects.requireNonNull(CoincheckTestUtil.class.getResourceAsStream(path), path);
	}

	public static ObjectMapper createObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
}