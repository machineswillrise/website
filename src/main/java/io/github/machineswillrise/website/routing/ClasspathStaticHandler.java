package io.github.machineswillrise.website.routing;

import java.net.URLConnection;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class ClasspathStaticHandler {
	private final String rootPage;
	
	public ClasspathStaticHandler(String rootPage) {
		this.rootPage = rootPage;
	}
	
	public HttpHandler create(HttpHandler fallback) {
		return exchange -> {
			String path = exchange.getRequestURI().getPath();
			if (path.isEmpty() || path.equals("/")) {
				path = rootPage;
			}

			InputStream resourceStream = ClasspathStaticHandler.class
				.getClassLoader().
				getResourceAsStream(path.substring(1));

			if (resourceStream == null) {
				fallback.handle(exchange);
				return;
			}

			String contentType = URLConnection.guessContentTypeFromName(path);
			if (contentType == null) {
				contentType = URLConnection.getFileNameMap().getContentTypeFor(path);
			}

			exchange.getResponseHeaders().set("Content-Type", contentType);
			exchange.sendResponseHeaders(200, 0);

			try (OutputStream os = exchange.getResponseBody()) {
				resourceStream.transferTo(os);
			}
			resourceStream.close();
			exchange.close();
		};
	}
}
