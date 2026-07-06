package io.github.machineswillrise.website.routing;

import java.util.concurrent.atomic.AtomicInteger;

public class IPRateLimit {
	private final AtomicInteger requestCount;
	private final long windowStart;

	public IPRateLimit() {
		this.requestCount = new AtomicInteger(1);
		this.windowStart = System.currentTimeMillis();
	}

	public boolean tryIncrement(int limit) {
		long now = System.currentTimeMillis();
		if (now - windowStart >= 60000) {
			requestCount.set(1);
			return true;
		}
		return requestCount.incrementAndGet() <= limit;
	}
}