package io.github.machineswillrise.website.routing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class RequestContext {
	private final HttpExchange exchange;
	private final Map<String, Set<String>> parameters;
	private Map<String, Set<String>> bodyParameters;
	private final Configuration freemarkerConfig;

	public RequestContext(HttpExchange exchange, Configuration freemarkerConfig) {
		this.exchange = exchange;
		this.parameters = parseQuery(exchange.getRequestURI().getQuery());
		this.freemarkerConfig = freemarkerConfig;
	}

	private Map<String, Set<String>> parseQuery(String query) {
		var result = new HashMap<String, Set<String>>();
		if (query == null) {
			return result;
		}

		var utf8 = StandardCharsets.UTF_8;
		for (var param : query.split("&")) {
			var entry = param.split("=", 2);
			if (entry.length > 0) {
				var key = URLDecoder.decode(entry[0], utf8);
				var value = (entry.length > 1) ? URLDecoder.decode(entry[1], utf8) : "";
				result.computeIfAbsent(key, k -> new HashSet<>()).add(value);
			}
		}

		return result;
	}

	public Set<String> getParameter(String key) {
		return parameters.getOrDefault(key, Collections.emptySet());
	}

	public String getSingleParam(String key, String defaultValue) {
		var values = parameters.get(key);
		if (values != null && !values.isEmpty()) {
			return values.iterator().next();
		}

		return defaultValue;
	}

	public void respond(int statusCode, String body) throws IOException {
		var bytes = body.getBytes();
		exchange.sendResponseHeaders(statusCode, bytes.length);
		try (var os = exchange.getResponseBody()) {
			os.write(bytes);
		}
	}

	public String getBody() throws IOException {
		try (var is = exchange.getRequestBody()) {
			return new String(is.readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	private Map<String, Set<String>> parseBody() throws IOException {
		var body = getBody();
		return parseQuery(body);
	}

	public String getBodyParam(String key, String defaultValue) throws IOException {
		if (bodyParameters == null) {
			bodyParameters = parseBody();
		}
		var values = bodyParameters.get(key);
		if (values != null && !values.isEmpty()) {
			return values.iterator().next();
		}
		return defaultValue;
	}

	public void renderTemplate(String templateName, Map<String, Object> data) throws IOException, TemplateException {
		Template template = freemarkerConfig.getTemplate(templateName);
		Writer out = new StringWriter();
		template.process(data, out);
		respond(200, out.toString());
	}
}