package io.github.machineswillrise.website.metrics;

import java.util.concurrent.atomic.LongAdder;

public class RequestCounter {
	private final LongAdder counter = new LongAdder();
	
	public void recordRequest() {
		counter.increment();
	}
	
	public long getRequestCount() {
		return counter.sum();
	}
}
