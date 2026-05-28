package io.github.machineswillrise.website.metrics;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class RequestCounter {
	private final LongAdder counter = new LongAdder();
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
	public RequestCounter() {
		scheduler.scheduleAtFixedRate(this::reset, 1, 1, TimeUnit.DAYS);
	}
	
	public void recordRequest() {
		counter.increment();
	}
	
	public long getRequestCount() {
		return counter.sum();
	}
	
	public void reset() {
		counter.reset();
	}
	
	public void shutdown() {
		scheduler.shutdown();
	}
}
