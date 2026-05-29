package io.github.machineswillrise.website.routing;

import java.io.OutputStream;
import java.net.URLConnection;

import com.sun.net.httpserver.HttpHandler;

public class ClasspathStaticHandler {
	private final String rootPage;
	
	public ClasspathStaticHandler(String rootPage) {
		this.rootPage = rootPage;
	}
	
	public HttpHandler create(HttpHandler fallback) {
		return exchange -> {
			var path = exchange.getRequestURI().getPath();
			if (path.isEmpty() || path.equals("/")) {
				path = rootPage;
			}

			try (exchange) {
				try (var resourceStream = ClasspathStaticHandler.class
					.getClassLoader().getResourceAsStream(path.substring(1))) {
					if (resourceStream == null) {
						fallback.handle(exchange);
						return;
					}

					var contentType = URLConnection.guessContentTypeFromName(path);
					if (contentType == null) {
						contentType = URLConnection.getFileNameMap().getContentTypeFor(path);
					}

					exchange.getResponseHeaders().set("Content-Type", contentType);
					exchange.sendResponseHeaders(200, 0);

					try (OutputStream os = exchange.getResponseBody()) {
						resourceStream.transferTo(os);
					}
				}
			}
		};
	}
}
