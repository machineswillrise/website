package io.github.machineswillrise.website.routing;

import java.io.IOException;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import io.github.machineswillrise.website.context.RequestContext;

public class Dispatcher implements HttpHandler {
	private static final Logger LOG = Logger.getLogger(Dispatcher.class.getName());

	private final Map<String, RouteAction> routes = new HashMap<>();
	private final Semaphore rateLimiter;

	public Dispatcher(int rateLimit) {
		this.rateLimiter = new Semaphore(rateLimit);
	}

	public void register(String path, RouteAction action) {
		routes.put(path, action);
	}

	private void sendErrorResponse(HttpExchange exchange, int status, String ip, String msg) throws IOException {
		var logEntry = "[" + ip + "] " + msg;
		var error = logEntry.getBytes();
		exchange.sendResponseHeaders(status, error.length);
		try (var os = exchange.getResponseBody()) {
			os.write(error);
		}
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String ip = exchange.getRemoteAddress().getAddress().getHostAddress();

		if (!rateLimiter.tryAcquire()) {
			LOG.warning("Rate limit exceeded for " + ip);
			sendErrorResponse(exchange, 429, ip, "429 Too Many Requests");
			return;
		}

		try {
			var path = exchange.getRequestURI().getPath();
			var action = routes.get(path);

			if (action == null) {
				sendErrorResponse(exchange, 404, ip, "404 Not Found");
				return;
			}

			var context = new RequestContext(exchange);
			action.execute(context);

		} catch (Exception e) {
			e.printStackTrace();
			sendErrorResponse(exchange, 500, ip, "500 Internal Server Error");
		} finally {
			rateLimiter.release();
		}

		LOG.info("Request handled for " + ip);
	}
}