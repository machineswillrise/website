package io.github.machineswillrise.website;

import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import java.net.InetSocketAddress;
import java.io.IOException;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

import io.github.machineswillrise.website.context.AppContext;
import io.github.machineswillrise.website.routing.ClasspathStaticHandler;

public class Website {
	private static final Logger LOG = Logger.getLogger(Website.class.getName());

	private static void configureLogging() {
		var logger = Logger.getLogger("");

		var stdoutHandler = new StreamHandler(System.out, new SimpleFormatter()) {
			@Override
			public synchronized void publish(LogRecord record) {
				super.publish(record);
				flush();
			}
		};

		stdoutHandler.setLevel(Level.INFO);
		logger.setLevel(Level.INFO);

		logger.setUseParentHandlers(false);
		logger.addHandler(stdoutHandler);
	}

	public static void main(String[] args) throws IOException {
		configureLogging();

		var context = new AppContext();
		var dispatcher = context.dispatcher;

		dispatcher.register("GET","/health", ctx -> ctx.respond(200, "OK"));
		dispatcher.register("POST", "/contact", ctx -> {
			var name = ctx.getBodyParam("name", "");
			var email = ctx.getBodyParam("email", "");
			var message = ctx.getBodyParam("message", "");

			var ntfyMessage = String.format("Contact form submission from %s (%s): %s", name, email, message);
			context.ntfyService.sendNotification(ntfyMessage);

			ctx.respond(200, "Message sent successfully!");
		});

		var port = new InetSocketAddress(context.PORT);
		var server = HttpServer.create(port, 0);
		var staticHandler = new ClasspathStaticHandler("/index.html");

		server.setExecutor(Executors.newCachedThreadPool());
		server.createContext("/", staticHandler.create(dispatcher));
		server.start();
	}
}
