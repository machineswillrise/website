package io.github.machineswillrise.website.context;

import java.net.http.HttpClient;

import io.github.machineswillrise.website.service.NtfyService;
import io.github.machineswillrise.website.routing.Dispatcher;

public class AppContext {
	// environment variables
	public final int PORT;
	private final int RATE_LIMIT; // per minute per IP
	private final String NTFY_URL;

	// dependencies
	private final HttpClient httpClient;

	// internals
	public final Dispatcher dispatcher;

	// actual services
	public final NtfyService ntfyService;

	public AppContext() {
		String port = getRequiredEnv("WEBSITE_PORT");
		String rateLimit = getRequiredEnv("WEBSITE_RATE_LIMIT");
		String ntfyUrl = getRequiredEnv("WEBSITE_NTFY_URL");

		this.PORT = Integer.parseInt(port);
		this.RATE_LIMIT = Integer.parseInt(rateLimit);
		this.NTFY_URL = ntfyUrl;

		this.dispatcher = new Dispatcher(RATE_LIMIT);

		this.httpClient = HttpClient.newHttpClient();

		this.ntfyService = new NtfyService(httpClient, NTFY_URL);
	}

	private String getRequiredEnv(String name) {
		String value = System.getenv(name);
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Environment variable " + name + " is not set or is empty");
		}
		return value;
	}
}
