package org.knowm.xchange.coingi.dto.trade;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class CoingiPaginatedResultList<T> implements Iterable<T> {
	private final boolean hasMore;

	public CoingiPaginatedResultList(boolean hasMore) {
		this.hasMore = hasMore;
	}

	public final boolean hasMore() {
		return hasMore;
	}

	@Override
	public Iterator<T> iterator() {
		return getList().iterator();
	}

	public final List<T> getList() {
		return Collections.unmodifiableList(getResultsList());
	}

	protected abstract List<T> getResultsList();

	@Override
	public final int hashCode() {
		return Objects.hash(hasMore, getResultsList());
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CoingiPaginatedResultList<?> that = (CoingiPaginatedResultList<?>) o;
		return hasMore == that.hasMore && Objects.equals(getResultsList(), that.getResultsList());
	}
}