package io.github.machineswillrise.website.service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import java.io.IOException;

import java.util.logging.Logger;

public class NtfyService {
	private static final Logger LOG = Logger.getLogger(NtfyService.class.getName());

	private final HttpClient httpClient;
	private final String ntfyUrl;
	
	public NtfyService(HttpClient httpClient, String ntfyUrl) {
		this.httpClient = httpClient;
		this.ntfyUrl = ntfyUrl;
	}
	
	public void sendNotification(String message) throws IOException, InterruptedException {
		LOG.info("Sending message to NTFY URL " + ntfyUrl + "with content: " + message);
		var request = HttpRequest.newBuilder()
			.uri(URI.create(ntfyUrl))
			.POST(HttpRequest.BodyPublishers.ofString(message))
			.build();
		
		httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	}
}
