package io.github.machineswillrise.website;

import java.net.http.HttpClient;
import java.util.Locale;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import io.github.machineswillrise.website.metrics.RequestCounter;
import io.github.machineswillrise.website.routing.Dispatcher;
import io.github.machineswillrise.website.service.NtfyService;

public class Context {
	// environment variables
	public final int PORT;
	private final int RATE_LIMIT; // per minute per IP
	private final String NTFY_URL;

	// freemarker
	private final Configuration freemarkerConfig;

	// dependencies
	private final HttpClient httpClient;

	// internals
	public final Dispatcher dispatcher;

	// actual services
	public final NtfyService ntfyService;
	public final RequestCounter requestCounter;

	public Context() {
		this.PORT = Integer.parseInt(getRequiredEnv("WEBSITE_PORT"));
		this.RATE_LIMIT = Integer.parseInt(getRequiredEnv("WEBSITE_RATE_LIMIT"));
		this.NTFY_URL = getRequiredEnv("WEBSITE_NTFY_URL");

		this.freemarkerConfig = new Configuration(Configuration.VERSION_2_3_32);
		this.freemarkerConfig.setClassForTemplateLoading(Context.class, "/templates");
		this.freemarkerConfig.setDefaultEncoding("UTF-8");
		this.freemarkerConfig.setLocale(Locale.US);
		this.freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		this.requestCounter = new RequestCounter();
		this.dispatcher = new Dispatcher(RATE_LIMIT, freemarkerConfig, requestCounter);

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
